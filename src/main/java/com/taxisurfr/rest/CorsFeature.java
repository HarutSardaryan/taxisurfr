package com.taxisurfr.rest;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.inject.Inject;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class CorsFeature implements Feature {

    @Inject
    Logger logger;
    @Override
    public boolean configure(FeatureContext context) {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        context.register(corsFilter);
        return true;
    }
}
