package com.taxisurfr.rest;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class RouteList {

    @ElementList
    public List<RouteData> list;

}


