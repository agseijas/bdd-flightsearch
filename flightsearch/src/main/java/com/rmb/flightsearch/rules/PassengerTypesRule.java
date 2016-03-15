package com.rmb.flightsearch.rules;

import java.math.BigDecimal;

import com.rmb.flightsearch.Flight;
import com.rmb.flightsearch.Logger;

public class PassengerTypesRule implements PassengerPriceRule{

    @Override
    public void apply(final Flight flight) {
        Logger.report("Applied rule(" + this.getClass().getName() +") for flight: " + flight.getAirline().getAirlineNumber());
        final DaysToDepartureRules departureDateRule = new DaysToDepartureRules();
        departureDateRule.apply(flight);
        if (flight.getChildren() > 0) {
            flight.setChildrenPrice(flight.getBasePrice().multiply(new BigDecimal("0.67")));
        }
        if (flight.getInfants() > 0) {
            final BigDecimal infantPrice = flight.getAirline().getInfantPrice();
            if(infantPrice != null) {
                flight.setInfantsPrice(infantPrice);
            }
        }

    }

}
