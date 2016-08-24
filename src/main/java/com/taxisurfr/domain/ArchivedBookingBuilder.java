package com.taxisurfr.domain;

/**
 * Created by nbb on 08.05.2016.
 */
public class ArchivedBookingBuilder {

    public ArchivedBooking getArchivedBooking(Booking booking)
    {
        ArchivedBooking archivedBooking = new ArchivedBooking();
        archivedBooking.setDate(booking.getDate());
        archivedBooking.setEmail(booking.getEmail());
        archivedBooking.setName(booking.getName());
        archivedBooking.setFlightNo(booking.getFlightNo());
        archivedBooking.setLandingTime(booking.getLandingTime());
        archivedBooking.setPax(booking.getPax());
        archivedBooking.setSurfboards(booking.getSurfboards());
        archivedBooking.setRequirements(booking.getRequirements());
        archivedBooking.setShareWanted(booking.getShareWanted());
        archivedBooking.setOrderType(booking.getOrderType());
        archivedBooking.setStatus(booking.getStatus());
        archivedBooking.setRoute(booking.getRoute());

        return archivedBooking;
    }
}
