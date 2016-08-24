package com.taxisurfr.util;


import com.taxisurfr.domain.Booking;
import com.taxisurfr.domain.PickupType;
import com.taxisurfr.domain.Route;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingUtil
{
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


    private static String FACEBOOK_APP = "https://apps.facebook.com/1651399821757463";
    private static String FACEBOOK_PAGE = "https://www.facebook.com/taxisurfr";


//    public String toConfirmationEmailHtml(Booking booking, Route route, String path, Profile profil)
//    {
//        String html = getTemplate(path);
//
//        String insertion = "";
//        for (Pair<String, String> pair : toPairList(booking,route))
//        {
//            insertion += pair.s1 + " " + pair.s2 + "<br>";
//        }
//        html = html.replace("____INSERT___DETAILS___", insertion);
//
//        String taxisurfrRouteLink = profil.getTaxisurfUrl() + "?route=" + booking.getRoute();
//        taxisurfrRouteLink = FACEBOOK_PAGE;
//
//        html = html.replace("__TAXISURFR_ROUTE_LINK__", taxisurfrRouteLink);
//        if (booking.getShareWanted()!=null && booking.getShareWanted())
//        {
//            html = html.replace("___SHARE_MESSAGE__", "Spread the word about your shared taxi using this share link.");
//
//        }
//        else
//        {
//            html = html.replace("___SHARE_MESSAGE__", "");
//        }
//
//        return html;
//
//    }

//    public String getTemplate(String path)
//    {
//        InputStream inputStream = getClass().getClassLoader()
//                .getResourceAsStream(path);
//
//        String line = null;
//        StringBuilder stringBuilder = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
//        {
//            String ls = System.getProperty("line.separator");
//
//            while ((line = reader.readLine()) != null)
//            {
//                stringBuilder.append(line);
//                stringBuilder.append(ls);
//            }
//        }
//        catch (IOException ex)
//        {
//            throw new RuntimeException(ex.getMessage());
//        }
//        return stringBuilder.toString();
//
//    }

}
