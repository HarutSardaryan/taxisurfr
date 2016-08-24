package com.taxisurfr.rest;

import com.taxisurfr.domain.*;
import com.taxisurfr.manager.*;
import com.taxisurfr.rest.js.StripePaymentJS;
import com.taxisurfr.util.PdfUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;
import java.util.List;


/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class RouteManagerIntegrationTest {

    @Inject
    RouteManager routeManager;

    @Inject
    BookingManager bookingManager;

    @Inject
    ContractorManager contractorManager;

    @Inject
    AgentManager agentManager;

    @Inject
    ProfileManager profilManager;

    //    WebArchive war = ShrinkWrap.create(WebArchive.class)
    //            .addPackage("de.atron.afed.service")
    //            .addPackage("de.atron.afed.dao")
    //            .addPackage("de.atron.afed.dao.to")
    //            .addPackage("de.atron.afed.dao.util")
    //            .addPackage("de.atron.afed.entity")
    //            .addPackage("de.atron.afed.util")
    //            .addPackage("de.atron.afed.listener")
    ////            .addAsLibraries(pomLibs)
    //            .addAsManifestResource("MANIFEST.MF")
    //            .addAsResource("META-INF/persistence.xml")
    //            .addPackages(true, "org.assertj.core");
    //
    //    return war;

    @Deployment
    public static Archive getDeployment() {
        File[] pomLibs1 =
            Maven.resolver().resolve("com.itextpdf:itextpdf:5.5.6").withTransitivity().asFile();
        File[] pomLibs2 =
            Maven.resolver().resolve("com.sendgrid:sendgrid-java:RELEASE").withTransitivity()
                .asFile();
        File[] pomLibs3 =
            Maven.resolver().resolve("commons-io:commons-io:2.1").withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "taxisurfrtest.war")
            .addPackage("com.taxisurfr.manager")
            //                .addClass("com.taxisurfr.manager.AbstractDao")
            //                .addClass("com.taxisurfr.manager.RouteManager")
            //                .addClass("com.taxisurfr.manager.ContractManager")
            //                .addClass("com.taxisurfr.manager.AgentManager")
            //                .addClass("com.taxisurfr.manager.BookingManager")
            //                .addClass("com.taxisurfr.manager.StripePayment")
            .addPackage("com.taxisurfr.domain")
            .addPackage("com.taxisurfr.dao")
            .addPackage("com.taxisurfr.util")
            .addPackage("com.taxisurfr.rest")
            .addPackage("com.taxisurfr.rest.js")
            .addPackage("org.apache.commons.io")
            .addAsLibraries(pomLibs1)
            .addAsLibraries(pomLibs2)
            .addAsLibraries(pomLibs3)
            .addAsResource("template/confirmation.html")
            .addAsResource("template/order.pdf")
            .addAsResource("META-INF/persistence.xml");
    }

    private long bookingId;

    @Before
    public void setup() {

        bookingManager.deleteAll();

        Agent agent = new Agent();
        agent.getOrderCount();
        agent.getOrderCount();
        agent.getOrderCount();
        agent.getOrderCount();
        agentManager.persist(agent);

        Contractor contractor = new Contractor();
        contractor.setAgentId(agent.getId());
        contractorManager.persist(contractor);

        Route route = new Route();
        route.setContractorId(contractor.getId());
        route.setCents(Long.valueOf(10000));

        routeManager.persist(route);
        Booking booking = new Booking();
        booking.setEmail("dispatch@taxisurfr.com");
        booking.setRoute(route.getId());


        bookingManager.persist(booking);
        bookingId = booking.getId();

        Profile profil = new Profile();
        profil.setStripeSecret("sk_test_TCIbuNPlBRe4VowPhqekTO1L");
        profil.setSendGridKey(
            "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");
        profil.setTest(true);
        profilManager.persist(profil);

        byte[] pdfData = new PdfUtil()
            .generateTaxiOrder("template/order.pdf", booking, route, agent, contractor);
        booking.setPdf(pdfData);
        bookingManager.edit(booking);
        bookingId = booking.getId();


    }



    @Inject
    TaxisurfrImpl taxisurfr;

    @Test
    public void should_create_payment() {
        StripePaymentJS stripePaymentJS = new StripePaymentJS();
        stripePaymentJS.bookingId = bookingId;
        taxisurfr.payment(stripePaymentJS);
    }

//    @Test
    public void should_get_pdf() {
        Booking b1= bookingManager.find(bookingId);
        System.out.println("pdf bytes"+b1.getPdf().length);


    }
}
