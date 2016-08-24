package com.taxisurfr.util;

import com.sendgrid.*;
import com.taxisurfr.domain.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;


//public class SendGridSender {
//    private static String DISPATCH = "dispatch@taxisurfr.com";
//
//    public static void main(String[] args)
//    {
//        new SendGridSender().send(null);
//    }

////    SENDGRID_HOSTNAME
////    smtp.sendgrid.net
////            SENDGRID_PASSWORD
////    adsRvqPZra
////            SENDGRID_USERNAME
////    DM12KPNLIq
//
//    public  void send(Logger logger) {
////        SendGrid sendgrid = new SendGrid(
////                System.getenv("SENDGRID_USERNAME","adsRvqPZra"),
////                System.getenv("SENDGRID_PASSWORD"));
//        SendGrid sendgrid = new SendGrid("adsRvqPZra","DM12KPNLIqx");
//
//        SendGrid.Email email = new SendGrid.Email();
//
//        email.addTo(DISPATCH);
//        email.setFrom(DISPATCH);
//        email.setSubject("Sending with SendGrid is Fun");
//        email.setHtml("and easy to do anywhere, even with Java");
//        System.out.println("finished");
//
//        try {
//            SendGrid.Response response = sendgrid.send(email);
//        } catch (SendGridException e) {
//            System.out.println(e.getMessage());
//            logger.severe(e.getMessage());
//        }
//    }
//}



public class SendGridSender {
    public static void main(String[]args){
        Logger logger = Logger.getLogger("test");
        SendGridSender sendGridExample = new SendGridSender();
        String toEmail = "dispatch@taxisurfr.com";
        String content = "<html>hello</html>";
        String role = "role";
        Booking booking = new Booking();
        Agent agent = new Agent();
        Route route = new Route();
        Contractor contractor = new Contractor();
        byte[] pdfData = new PdfUtil().generateTaxiOrder("template/test.pdf", booking, route, agent, contractor);

        Profile profile = new Profile();
        profile.setSendGridKey("key here");
        sendGridExample.send(logger,toEmail,content,role,profile);
    }

    public void send(Logger logger, String toEmail, String htmlBody, String role, Profile profile) {
        Email from = new Email("dispatch@taxisurfr.com");
        String subject = "Confirmation";
        Email to = new Email("dispatch@taxisurfr.com");
        Content content = new Content("text/html", htmlBody);
        Mail mail = new Mail(from, subject, to, content);

//        Attachments attachments = new Attachments();
//        String s = null;
//        try {
//            s = new String(pdfData, 0, pdfData.length, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        logger.info("length:"+s.length());
//        logger.info(s.substring(0,s.length()<20 ? s.length()-1:19));
//            attachments.setContent(s);
//        attachments.setType("application/pdf");
//        attachments.setFilename("order.pdf");
//        attachments.setDisposition("attachment");
//        attachments.setContentId("Order Confirmation");
//        mail.addAttachments(attachments);


//        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        logger.info("key"+profile.getSendGridKey());
        SendGrid sg = new SendGrid(profile.getSendGridKey());
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        }
    }
}
