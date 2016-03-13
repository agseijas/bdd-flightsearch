package com.rmb.flightsearch;

import java.util.function.Consumer;

import com.rmb.flightsearch.FlightSearchCriteria;

public class Logger {

    public static Consumer<String> CUSTOM_REPORTER = System.out::println;

    public static void report(final FlightSearchCriteria request) {
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
