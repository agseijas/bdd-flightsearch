package com.rmb.flightsearch.rules;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.rmb.flightsearch.Flight;

public class DaysToDepartureRules implements PassengerPriceRule {

    @Override
    public void apply(final Flight flight) {
        final ZonedDateTime now = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        final ZonedDateTime flightDepartureDate = flight.getDepartureDate().withHour(0).withMinute(0).withSecond(0).withNano(0);

        if(now.plusDays(30).isBefore(flightDepartureDate)) {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(new BigDecimal("0.8")));
        } else if (now.plusDays(16).isBefore(flightDepartureDate) || now.plusDays(16).isEqual(flightDepartureDate)) {
            flight.setBasePrice(flight.getAirline().getPrice());
        } else if (now.plusDays(3).isBefore(flightDepartureDate)  || now.plusDays(3).isEqual(flightDepartureDate)) {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(new BigDecimal("1.2")));
        } else {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(new BigDecimal("1.5")));
        }
    }


}
