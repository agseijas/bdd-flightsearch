package com.rmb.flightsearch;

import java.util.function.Consumer;

public class Logger {

    public static Consumer<String> CUSTOM_REPORTER = System.out::println;

    public static final boolean REPORT_ACTIVE = Boolean.parseBoolean(System.getProperty("active.logging", "true"));

    /**
     * Reports the flight request for search using this class' CUSTOM_REPORTER.
     * @param request
     */
    public static void reportFlightSearch(final FlightSearchCriteria request) {
        if(REPORT_ACTIVE){
            CUSTOM_REPORTER.accept("Searching for flight with this data:");
            CUSTOM_REPORTER.accept("------------------------------------");
            CUSTOM_REPORTER.accept("originCity: " + request.getOriginIATA());
            CUSTOM_REPORTER.accept("destinationCity: " + request.getDestinationIATA());
            CUSTOM_REPORTER.accept("departureDateFromToday: " + request.getDepartureDate());
            CUSTOM_REPORTER.accept("adults: " + request.getAdults());
            CUSTOM_REPORTER.accept("children: " + request.getChildren());
            CUSTOM_REPORTER.accept("infants: " + request.getInfants());
            CUSTOM_REPORTER.accept("------------------------------------");
        }
    }

    public static void report(final String message) {
        if(REPORT_ACTIVE){
            CUSTOM_REPORTER.accept(message);
        }
    }
}
