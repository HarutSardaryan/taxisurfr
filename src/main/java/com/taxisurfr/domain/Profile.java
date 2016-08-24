package com.taxisurfr.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
public class Profile implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "idGeneratorSeq", sequenceName = "idSequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGeneratorSeq")
    private long id;

    @Column private boolean test;


    public void setMonitorMobile(Long monitorMobile) {
        this.monitorMobile = monitorMobile;
    }

    @Column private String name;
    @Column private String monitorEmail;
    @Column private Long monitorMobile = 491709025959L;
    @Column private String stripeSecret = "sk_test_TCIbuNPlBRe4VowPhqekTO1L";
    @Column private String stripePublishable = "pk_test_rcKuNpP9OpTri7twmZ77UOI5";



    @Column private String sendGridKey;
    private String smspassword;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getStripeSecret()
    {
        return stripeSecret;
    }

    public void setStripeSecret(String stripeSecret)
    {
        this.stripeSecret = stripeSecret;
    }

    public String getStripePublishable()
    {
        return stripePublishable;
    }

    public void setStripePublishable(String stripePublishable)
    {
        this.stripePublishable = stripePublishable;
    }

    private String taxisurfUrl;

    private static final String TAXIGANGSURF_URL = "https://taxisurfr.com";

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isTest()
    {
        return test;
    }

    public void setTest(boolean test)
    {
        this.test = test;
    }

    public String getMonitorEmail()
    {
        return monitorEmail;
    }

    public void setMonitorEmail(String monitorEmail)
    {
        this.monitorEmail = monitorEmail;
    }

//    public ProfilInfo getInfo()
//    {
//        ProfilInfo info = new ProfilInfo();
//        info.setStripePublishable(stripePublishable);
//        info.setName(name);
//        return info;
//    }

    public String getTaxisurfUrl()
    {
        if (taxisurfUrl == null)
        {
            return TAXIGANGSURF_URL;

        }
        return taxisurfUrl;
    }

    public void setTaxisurfUrl(String taxisurfUrl)
    {
        this.taxisurfUrl = taxisurfUrl;
    }

    public String getSmspassword()
    {
        return smspassword;
    }

    public void setSmspassword(String smspassword)
    {
        this.smspassword = smspassword;
    }

    public Long getMonitorMobile()
    {
        return monitorMobile;
    }

    public void setMonitorMobile(long monitorMobile)
    {
        this.monitorMobile = monitorMobile;
    }

    public String getSendGridKey() {
        return sendGridKey;
    }

    public void setSendGridKey(String sendGridKey) {
        this.sendGridKey = sendGridKey;
    }
}
