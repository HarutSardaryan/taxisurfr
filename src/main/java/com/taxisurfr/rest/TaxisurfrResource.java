package com.taxisurfr.rest;

import com.taxisurfr.domain.Booking;
import com.taxisurfr.domain.Route;
import com.taxisurfr.domain.SessionStat;
import com.taxisurfr.domain.StatInfo;
import com.taxisurfr.rest.js.NewSessionJS;
import com.taxisurfr.rest.js.Query;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public interface TaxisurfrResource {

    @GET
    @Path("/routes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Route> getRoutes() throws IllegalArgumentException;

    @GET
    @Path("/routes.start")
    public List<Route> getRoutesStart(@Named("query") String query) throws IllegalArgumentException;

    @POST
    @Path("/routes.end")
    public List<Route> getRoutesEnd(Query query) throws IllegalArgumentException;

    @POST
    @Path("/routes.query")
    public List<Route> getRoutesByQuery(@Named("query") String query) throws IllegalArgumentException;

    @POST
    @Path("/route.link")
    public Route getRouteById(@Named("routeId") String routeId) throws IllegalArgumentException;

    @POST
    @Path("/session.get")
    public SessionStat getStat(SessionStat route) throws IllegalArgumentException;

    @POST
    @Path("/session.new")
    public SessionStat addSession(NewSessionJS newSessionJS) throws IllegalArgumentException;

    @POST
    @Path("/booking.new")
    public Booking addBooking(Booking booking) throws IllegalArgumentException;

    @POST
    @Path("/booking.pay")
    public Booking payBooking(SessionStat sessionStat);

}
