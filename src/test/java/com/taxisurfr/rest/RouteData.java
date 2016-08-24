package com.taxisurfr.rest;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class RouteData {

    @Element
    public String id;

    @Element
    public String start;

    @Element
    public String cents;

    @Element(required=false)  public String agentCents;
    @Element  public String startEnd;
    @Element(required=false)  public String link;
    @Element  public String inactive;
    @Element  public String image;
    @Element  public String end;
    @Element  public String description;
    @Element  public String contractorId;
    @Element(required=false)  public String associatedRoute;

    @Element
    public String pickupType;

//    <id>4509291868323840</id>
//    <pickupType>AIRPORT</pickupType>
//    <cents>10000</cents>
//    <startEnd>Colombo AirporttoHikkaduwa</startEnd>
//    <link>hikkaduwa</link>
//    <inactive>false</inactive>
//    <image>5185581579501568</image>
//    <start>Colombo Airport</start>
//    <end>Hikkaduwa</end>
//    <description>Pickup at the airport. The trip is 140km and takes approximately 3 hours. All expenses are included.</description>
//    <contractorId>4699290953842688</contractorId>

    public RouteData() {
        super();
    }

//    public Route(String text, int index) {
//        this.text = text;
//        this.index = index;
//    }


}