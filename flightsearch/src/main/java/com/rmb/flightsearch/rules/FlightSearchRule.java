package com.rmb.flightsearch.rules;

import com.rmb.flightsearch.Flight;

public interface FlightSearchRule {

    /**
     * Applies this rule to the parameterized flight.
     *
     * @param flight
     */
    void apply(Flight flight);

    /**
     * Prepares this rule so that it can prepare the data it'll apply later.
     */
    void prepare();
}
