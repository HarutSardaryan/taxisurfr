package com.taxisurfr.domain;


import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class SessionStat implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "idGeneratorSeq", sequenceName = "idSequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGeneratorSeq")
    private long id;

    String type;
    String route;
    String userAgent;
    Currency currency;
    Float currencyRate;
    Integer interactions = 0;



    String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    String referer;
    String routeKey;

    @Column
    LocalDateTime time;

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    String cardToken;
    Long bookingId;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Float getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Float currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getRoute()
    {
        return route;
    }

    public void setRoute(String route)
    {
        this.route = route;
    }

    @Column
    String src;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    String country;

//    public static SessionStat getSessionStat(StatInfo statInfo)
//    {
//        SessionStat stat = new SessionStat();
//        stat.referer = statInfo.getReferer();
//        stat.routeKey = statInfo.getRouteKey();
//        stat.time = statInfo.getTime();
//        stat.setCountry(statInfo.getCountry());
//        stat.setType(statInfo.getDetail());
//        stat.src = statInfo.getSrc();
//        stat.currency = statInfo.getCurrency();
//        stat.currencyRate = statInfo.getCurrencyRate();
//        return stat;
//    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    //    @Override
//    public StatInfo getInfo()
//    {
//        StatInfo statInfo = new StatInfo();
//        statInfo.setReferer(referer);
//        statInfo.setRouteKey(routeKey);
//        statInfo.setTime(time);
//        statInfo.setCountry(country);
//        statInfo.setSrc(src);
//        statInfo.setCurrency(currency);
//        statInfo.setCurrencyRate(currencyRate);
//        return statInfo;
//    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void incInteractions(){
        interactions++;
    }
}
