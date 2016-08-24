package com.taxisurfr.rest.js;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StripePaymentJS {
    public String token;
    public Long bookingId;
}


//id: "tok_18cT9O2dwz89c3UCMbY1p3Lc", object: "token", card: Object, client_ip: "91.34.121.236", created: 1469768590…