package com.rmb.flightsearch;

public class TestUtils {

    private static final String TOTALLY_WRONG_AIRLINE = "TOTALLY_WRONG_AIRLINE";

    final static Airline WRONG_AIRLINE = new Airline.buildWith()
            .airline(TOTALLY_WRONG_AIRLINE)
            .price("-10000000")
            .origin(TOTALLY_WRONG_AIRLINE)
            .destination(TOTALLY_WRONG_AIRLINE)
            .createAirline();

}
