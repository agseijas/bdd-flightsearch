package com.rmb.flightsearch.rules;

import com.rmb.flightsearch.Flight;

public interface PassengerPriceRule {

    /**
     * Applies this rule to the parameterized flight.
     * @param flight
     */
    void apply(Flight flight);

}
