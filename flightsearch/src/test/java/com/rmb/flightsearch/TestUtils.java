package com.rmb.flightsearch;

public class TestUtils {

    private static final String TOTALLY_WRONG_AIRLINE = "TOTALLY_WRONG_AIRLINE";
    private static final String CORRECT_AIRLINE_NUMBER = "TK8891";

    public final static Airline WRONG_AIRLINE = new Airline.buildWith()
            .airline(TOTALLY_WRONG_AIRLINE)
            .price("-10000000")
            .origin(TOTALLY_WRONG_AIRLINE)
            .destination(TOTALLY_WRONG_AIRLINE)
            .createAirline();

    public final static Airline VALID_AIRLINE = new Airline.buildWith()
            .airline(CORRECT_AIRLINE_NUMBER)
            .price("123")
            .origin("CPH")
            .destination("LHR")
            .createAirline();
}
