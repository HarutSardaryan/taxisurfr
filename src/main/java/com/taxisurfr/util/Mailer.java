package com.taxisurfr.util;


import com.taxisurfr.domain.*;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Model
public class Mailer {
    @Inject
    Logger logger;

    private Map<String, File> templateMap = new HashMap<>();

    private final String DISPATCHER = "dispatch@taxisurfr.com";
    private final String EMAILIT = "peter24lasagna@emailitin.com";
    private static final String ROUTE = "Route:";
    private static final String DATE = "Date:";
    private static final String FLIGHTNO = "Flight No:";
    private static final String HOTEL = "Hotel:";
    private static final String LANDING_TIME = "Landing Time:";
    private static final String PICKUP_TIME = "Pickup Time:";
    private static final String NAME = "Name:";
    private static final String EMAIL = "Email:";
    private static final String NUM_PAX = "Passengers:";
    private static final String NUM_SURFBOARDS = "Surfboards:";
    private static final String REQS = "Other requirements:";
    private static DateTimeFormatter sdf = DateTimeFormatter.ISO_DATE;

    private SendGridSender sender = new SendGridSender();
    ;

    static class Pair<S, S1> {
        String s1;
        String s2;

        static Pair of(String s1, String s2){
            Pair pair = new Pair();
            pair.s1 = s1;
            pair.s2 = s2;
            return pair;

        }
    }

    private File getFile(String path) {
        File file = templateMap.get(path);
        if (file == null) {
            templateMap.put(path, new File(path));
        }
        return templateMap.get(path);
    }

    public static final String SHARE_REQUEST = "template/abay_share_request.html";
    public static final String FEEDBACK_REQUEST = "template/feedbackRequest.html";
    public static final String SHARE_ACCEPTED = "template/abay_share_request_accepted.html";
    public static final String CONFIRMATION = "template/confirmation.html";


    public void sendConfirmation(Booking booking, Route route, Profile profile, Agent agent, Contractor contractor) {
        String html = "error";
        html = toConfirmationEmailHtml(booking, route, CONFIRMATION, profile);
        html = html.replace("__CONFIMATION__", "Booking Confirmation");

        String email = booking.getEmail();
        sender.send(logger,booking.getEmail(), html, "customer",profile);
//        send(profil.getMonitorEmail(), html, "monitor");
//        send(agent.getEmail(), html, "agent");
//        if (contractor != null) {
//            send(contractor.getEmail(), html, "contractor");
//        }
//        send(null, null, null);
//        emailit(pdfData, booking.getRef());
    }

    private static String FACEBOOK_PAGE = "https://www.facebook.com/taxisurfr";

    public String toConfirmationEmailHtml(Booking booking, Route route, String path, Profile profil) {
        try {
            String html = getTemplate(path);

            String insertion = "";
            for (Pair<String, String> pair : toPairList(booking, route)) {
                insertion += pair.s1 + " " + pair.s2 + "<br>";
            }
            html = html.replace("____INSERT___DETAILS___", insertion);

            String bookingLink = "https://taxisurfr-taxisurfr.rhcloud.com/rest/api/form?id="+booking.getId();
                html = html.replace("___BOOKING_LINK___", bookingLink);


            String taxisurfrRouteLink = profil.getTaxisurfUrl() + "?route=" + booking.getRoute();
            taxisurfrRouteLink = FACEBOOK_PAGE;

            html = html.replace("__TAXISURFR_ROUTE_LINK__", taxisurfrRouteLink);
            if (booking.getShareWanted() != null && booking.getShareWanted()) {
                html = html.replace("___SHARE_MESSAGE__", "Spread the word about your shared taxi using this share link.");

            } else {
                html = html.replace("___SHARE_MESSAGE__", "");
            }

            return html;
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Pair<String, String>> toPairList(Booking booking, Route route) {
        List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();

        list.add(Pair.of(ROUTE, route.getStartroute() + " to " + route.getEndroute()));
        list.add(Pair.of(DATE, booking.getDateText()));
        PickupType pickupType = route.getPickupType();
        list.add(Pair.of(pickupType.getLocationType(), booking.getFlightNo()));
        list.add(Pair.of(pickupType.getTimeType(), booking.getLandingTime()));
        list.add(Pair.of(NAME, booking.getName()));
        list.add(Pair.of(EMAIL, booking.getEmail()));
        list.add(Pair.of(NUM_SURFBOARDS, Integer.toString(booking.getSurfboards())));
        list.add(Pair.of(NUM_PAX, Integer.toString(booking.getPax())));
        list.add(Pair.of(REQS, booking.getRequirements()));

        return list;
    }

    public String getTemplate(String path) {
        logger.info("path:" + path);
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(path);

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return stringBuilder.toString();

    }

}
