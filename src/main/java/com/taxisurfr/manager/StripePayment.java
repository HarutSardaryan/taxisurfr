package com.taxisurfr.manager;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.taxisurfr.domain.Booking;
import com.taxisurfr.domain.OrderStatus;
import com.taxisurfr.domain.Route;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class StripePayment
{
    private static final Logger logger = Logger.getLogger(StripePayment.class.getName());

    public String charge(String card, Booking booking, String stripeSecret, Route route)
    {
        assert booking != null;
        assert route != null;
        String error = null;
//        try
//        {
//            Stripe.apiKey = stripeSecret;
//
//            Map<String, Object> chargeParams = new HashMap<String, Object>();
//            int cents = route.getCents().intValue();
//
//            chargeParams.put("amount", cents);
//            chargeParams.put("currency", booking.getCurrency().name().toLowerCase());
//            chargeParams.put("card", card); // obtained with Stripe.js
//            chargeParams.put("description", "Taxi Charges Sri Lanka - "+booking.getRef()+" - Thank you!");
//            logger.info("receipt to "+booking.getEmail());
//            chargeParams.put("receipt_email", booking.getEmail());
//            //chargeParams.put("receipt_email", "hall@silvermobilityservices.com");
//
//
//            logger.info("charging cents " + booking.getCurrency().name().toLowerCase() + cents);
//            Charge charge = Charge.create(chargeParams);
//
//            booking.setStatus(OrderStatus.PAID);
//            booking.setPaidPrice(cents);
//            logger.info("charging successful");
//            return error;
//        }
//
//        catch (Exception exception)
//        {
//            error = exception.getMessage();
//            logger.log(Level.SEVERE, exception.getMessage(), exception);
//        }
        return error;
    }

}
