package com.rmb.flightsearch.rules;

import com.rmb.flightsearch.Flight;

public class PassengerTypeRule implements PassengerPriceRule{

    @Override
    public void apply(final Flight flight) {
        final DaysToDepartureRules departureDateRule = new DaysToDepartureRules();
        departureDateRule.apply(flight);
        if (flight.getAdults() > 0) {

        }
        if (flight.getChildren() > 0) {

        }
        if (flight.getInfants() > 0) {

        }

    }

}
