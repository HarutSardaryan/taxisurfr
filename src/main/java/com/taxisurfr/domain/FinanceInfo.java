package com.taxisurfr.domain;

import java.util.Date;

public class FinanceInfo extends Info
{
    public enum Type
    {
        PAYMENT,
        TRANSFER
    };

    private static final long serialVersionUID = 1L;

    private Date date;
    private Date deliveryDate;
    private Type type;

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    private String name;
    private String order;
    private Long amount;
    private Long bookingId;
    private Long agentId;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public Long getAmount()
    {
        return amount;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public void setBookingId(Long bookingId)
    {
        this.bookingId = bookingId;
    }

    public Long getBookingId()
    {
        return bookingId;

    }

    public Long getAgentId()
    {
        return agentId;
    }

    public void setAgentId(Long agentId)
    {
        this.agentId = agentId;
    }
}
