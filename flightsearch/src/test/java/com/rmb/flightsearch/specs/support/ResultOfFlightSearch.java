package com.rmb.flightsearch.specs.support;

import java.util.Set;

import com.rmb.flightsearch.Flight;

public class ResultOfFlightSearch {

    Set<Flight> flights;

    public ResultOfFlightSearch(final Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<Flight> getFlights() {
        return flights;
    }
}
