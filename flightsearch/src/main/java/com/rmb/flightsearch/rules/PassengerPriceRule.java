package com.rmb.flightsearch.rules;

import com.rmb.flightsearch.Flight;

public interface PassengerPriceRule {

    void apply(Flight flight);

}
