package com.taxisurfr.rest.js;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nbb on 05.04.2016.
 */
@XmlRootElement
public class NewSessionJS {
    public String src;
    public String start;
    public String end;
    public boolean test;
    public String url;
}
