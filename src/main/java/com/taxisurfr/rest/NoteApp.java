package com.taxisurfr.rest;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@ApplicationPath("/rest")
public class NoteApp extends Application {
}
