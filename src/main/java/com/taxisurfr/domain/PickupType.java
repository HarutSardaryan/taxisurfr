package com.taxisurfr.domain;

public enum PickupType
{

    HOTEL("Hotel", "Pickup Time"),
    AIRPORT("Flight No", "Landing Time"),
    TRAINSTATION("Train station", "Train arrival time");

    String locationType;
    String timeType;

    private PickupType(String locationType, String timeType)
    {
        this.locationType = locationType;
        this.timeType = timeType;
    }

    public String getLocationType()
    {
        return locationType;
    }

    public String getTimeType()
    {
        return timeType;
    }
}