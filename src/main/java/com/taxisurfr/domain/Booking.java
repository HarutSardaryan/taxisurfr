package com.taxisurfr.domain;

import com.itextpdf.text.Phrase;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@XmlRootElement
public class Booking implements java.io.Serializable {
    private static final long serialVersionUID = 1L;


    public Booking()
    {
        orderStatus = OrderStatus.BOOKED;
        orderType = OrderType.BOOKING;
        instanziated = LocalDateTime.now();

        rated = false;
    }

    @Id
    @SequenceGenerator(name = "idGeneratorSeq", sequenceName = "idSequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGeneratorSeq")
    private long id;

    @Column private String name;
    @Column private String email;
    @Column private String dateText;
    @Column private String flightNo;
    @Column private String landingTime;
    @Column private int pax;
    @Column private int surfboards;
    @Column private OrderStatus orderStatus;
    @Column private String requirements;
    @Lob private byte[] pdf;
    @Column private String ref;
    @Column private Boolean shareWanted;
    @Column private LocalDateTime instanziated;
    @Column private OrderType orderType;
    private Long parentId;
    @Column private Long route;
    private Boolean rated;
    private String stripeRefusal;

    @Column private LocalDateTime date;

    @Column private Currency currency = Currency.USD;
    public LocalDateTime getDate()
    {
        return date;
    }
    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }
    private int paidPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPaidPrice()
    {
        return paidPrice;
    }

    public void setPaidPrice(int paidPrice)
    {
        this.paidPrice = paidPrice;
    }

    public Long getRoute()
    {
        return route;
    }

    public void setRoute(Long route)
    {
        this.route = route;
    }

    public Boolean getRated()
    {
        return rated;
    }
    public void setRated(Boolean rated)
    {
        this.rated = rated;
    }

    public void setRated(boolean rated)
    {
        this.rated = rated;
    }

    public Boolean getShareWanted()
    {
        return shareWanted;
    }

    public void setShareWanted(Boolean shareWanted)
    {
        this.shareWanted = shareWanted;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public OrderType getOrderType()
    {
        return orderType;
    }

    public void setOrderType(OrderType orderType)
    {
        this.orderType = orderType;
    }

    public LocalDateTime getInstanziated()
    {
        return instanziated;
    }

    public void setInstanziated(LocalDateTime instanziated)
    {
        this.instanziated = instanziated;
    }

    public void setStatus(OrderStatus status)
    {
        this.orderStatus = status;
    }

    public OrderStatus getStatus()
    {
        return orderStatus;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getPax()
    {
        return pax;
    }

    public void setPax(int pax)
    {
        this.pax = pax;
    }

    public String getRequirements()
    {
        return requirements;
    }

    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public int getSurfboards()
    {
        return surfboards;
    }

    public void setSurfboards(int surfboards)
    {
        this.surfboards = surfboards;
    }


    public String getDateText()
    {
        return dateText;
    }

    public void setDateText(String dateText)
    {
        this.dateText = dateText;
    }

    public String getFlightNo()
    {
        return flightNo;
    }

    public void setFlightNo(String flightNo)
    {
        this.flightNo = flightNo;
    }

    public String getLandingTime()
    {
        return landingTime;
    }

    public void setLandingTime(String landingTime)
    {
        this.landingTime = landingTime;
    }

    public byte[] getPdf()
    {
        return pdf;
    }

    public void setPdf(byte[] pdf)
    {
        this.pdf =pdf;
    }

    public String getRef()
    {
        return ref;
    }

    public void setRef(String ref)
    {
        this.ref = ref;
    }

    public int compareTo(Booking other)
    {
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than
        // other and 0 if they are supposed to be equal
        return this.instanziated.isAfter(instanziated) ? -1 : -1;
    }


    private void setCurrency(Currency currency)
    {
        this.currency = currency;
    }
    public Currency getCurrency() {
        return currency;
    }

    public void setStripeRefusal(String stripeRefusal) {
        this.stripeRefusal = stripeRefusal;
    }
    public String getStripeRefusal(){
        return stripeRefusal;
    }

    public String getOrderRef() {
        return "FIXE";
    }
}
