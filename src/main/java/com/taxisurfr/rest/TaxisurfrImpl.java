package com.taxisurfr.rest;

import com.taxisurfr.domain.*;
import com.taxisurfr.manager.*;
import com.taxisurfr.rest.js.*;
import com.taxisurfr.util.Mailer;
import com.taxisurfr.util.PdfUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Path("/api")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
//@Consumes("application/json")
public class TaxisurfrImpl {

    @Inject
    private BookingManager bookingManager;

    @Inject
    RouteManager routeManager;
    @Inject
    AgentManager agentManager;

    @Inject
    ContractorManager contractorManager;

    @Inject
    StatManager statManager;

    @Inject
    StripePayment stripePayment;

    @Inject
    ProfileManager profileManager;

    @Inject
    Logger logger;

    @Inject
    Mailer mailer;

    @Produces(MediaType.APPLICATION_JSON)
    public List<Route> getRoutes() throws IllegalArgumentException {
        return routeManager.getRoutes();
    }

    @POST
    @Path("/session.get")
    public NewSessionResultJS addSession(NewSessionJS session) throws IllegalArgumentException {
        logger.info("session.new" + session.start + " to " + session.end);
        SessionStat sessionStat = statManager.addSession(session);

        NewSessionResultJS newSessionResultJS = new NewSessionResultJS();
        newSessionResultJS.stripePublishable = profileManager.getProfile().getStripePublishable();

        return newSessionResultJS;
    }

    @POST
    @Path("/route")
    public Route getRoute(Query query) throws IllegalArgumentException {
        logger.info("start:" + query.start + "end:" + query.end);
        Route route = routeManager.getRoute(query);
        logger.info("route:" + route);
        return route;
    }

    @POST
    @Path("/missingroute")
    public Route missingRoute(Query query) throws IllegalArgumentException {
        logger.severe(" missing start:" + query.start + "end:" + query.end);
        return null;
    }

    @POST
    @Path("/booking")
    public Booking addBooking(NewBookingJS booking) throws IllegalArgumentException {
        logger.info("addBooking");
        return bookingManager.createBooking(booking);
    }

    @POST
    @Path("/payment")
    public PaymentResultJS payment(StripePaymentJS stripePaymentJS)
        throws IllegalArgumentException {
        PaymentResultJS paymentResultJS = new PaymentResultJS();
        assert stripePaymentJS.bookingId != null;
        logger.info("bookingId:" + stripePaymentJS.bookingId);
        try {

            Booking booking = bookingManager.find(stripePaymentJS.bookingId);
            Route route = routeManager.find(booking.getRoute());
            assert route != null;
            assert route.getContractorId() != null;
            Contractor contractor = contractorManager.find(route.getContractorId());
            logger.info("contractor with id:" + route.getContractorId());
            logger.info("contractor :" + contractor);
            assert contractor != null;
            assert contractor.getAgentId() != null;

            Agent agent = agentManager.find(contractor.getAgentId());
            Profile profile = profileManager.getProfile();
            assert profile != null;
            //            String stripeSecret = "sk_test_TCIbuNPlBRe4VowPhqekTO1L";
            paymentResultJS.error = stripePayment
                .charge(stripePaymentJS.token, booking, profile.getStripeSecret(), route);
            logger.info("stripe error:" + paymentResultJS.error);
            if (paymentResultJS.error == null) {
                booking.setPaidPrice((route.getCents().intValue()));
                booking.setRef(agent.getOrderCount()+"_"+booking.getName());
                byte[] pdfData = new PdfUtil()
                    .generateTaxiOrder("template/order.pdf", booking, route, agent, contractor);
                booking.setPdf(pdfData);
                bookingManager.edit(booking);
                paymentResultJS.ok = true;
                mailer.sendConfirmation(booking, route, profile, agent, contractor);
                agent.getOrderCount();
            } else {
                paymentResultJS.ok = true;
            }
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
            paymentResultJS.error =
                "Sorry about that. We had an internal error. Please contact dispatch@taxisurf.com";
            paymentResultJS.ok = false;
            ex.printStackTrace();
        }

        return paymentResultJS;
    }

    @GET
    @Path("/routes.start")
    public List<String> getRoutesStart(@QueryParam("query") String query)
        throws IllegalArgumentException {
        logger.info("query start:" + query);
        //        return routeManager.getRoutesStart(query);
        return Arrays.asList("hello1", "hello2");
    }

    @GET
    @Path("/form")
    public Response confirmationForm(@Context HttpServletRequest request, @Context
        HttpServletResponse response, @QueryParam("id") Long id) throws IllegalArgumentException {
        logger.info("id:" + id);
        Booking booking = bookingManager.find(id);
        if (booking == null){
            return null;
        }
        byte[] pdf = bookingManager.find(id).getPdf();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=confirmation.pdf");
        response.setContentLength(pdf.length);
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(pdf);

            out.flush();
            out.close();

        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        return null;
    }

    public List<Route> getRoutesEnd(Query query) throws IllegalArgumentException {
        logger.info("query start:" + query.start + " end:" + query.end);
        List<Route> result = routeManager.getRoutesFromQuery(query.start, query.end);
        logger.info("result size:" + result.size());
        return result;

    }

    public Booking payBooking(SessionStat sessionStat) {

        return null;
        //        Booking booking = bookingManager.getBooking(sessionStat.getBookingId());
        //        Profile profil = bookingManager.getProfil();
        //        Route route = ofy().load().type(Route.class).id(booking.getRoute()).now();
        //        booking.setPaidPrice(route.getCents().intValue() / 100);
        //        Contractor contractor = contractorManager.getContractor(route);
        //        Agent agent = agentManager.getAgent(contractor);
        //        Long orderCount = agent.getOrderCount();
        //        booking.setRef(orderCount + "_" + booking.getName());
        //
        //        String refusal = stripePayment.charge(sessionStat.getCardToken(), booking, profil.getStripeSecret());
        //        if (refusal == null) {
        //            booking = bookingManager.setPayed(profil, booking);
        //            if (booking != null) {
        //                Mailer.sendConfirmation(booking, route, profil, agent, contractor);
        //                financeManager.addPayment(booking, new Date());
        //                ofy().save().entity(agent).now();
        //            }
        //        } else {
        //            booking.setStripeRefusal(refusal);
        //        }
        //        return booking;
    }
}
