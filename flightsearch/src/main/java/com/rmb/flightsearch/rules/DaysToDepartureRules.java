package com.rmb.flightsearch.rules;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.rmb.flightsearch.Flight;

public class DaysToDepartureRules implements PassengerPriceRule {

    @Override
    public void apply(final Flight flight) {
        final ZonedDateTime now = ZonedDateTime.now();

        if(now.plusDays(30).isAfter(flight.getDepartureDate())) {
            flight.setAdultsPrice(flight.getAirline().getPrice().multiply(new BigDecimal("0.8")));
        }
    }


}
