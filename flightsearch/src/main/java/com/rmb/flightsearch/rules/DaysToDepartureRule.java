package com.rmb.flightsearch.rules;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.rmb.flightsearch.Flight;
import com.rmb.flightsearch.Logger;

public class DaysToDepartureRule extends RuleWithConfiguration {

    private BigDecimal priceMultiplierCommonDates;
    private BigDecimal priceMultiplierNearestDates;
    private BigDecimal priceMultiplierSecondNearestDates;
    private BigDecimal priceMultiplierMostDistantDates;

    @Override
    public void apply(final Flight flight) {
        Logger.report("Applied rule(" + this.getClass().getName() + ") for flight: "
                + flight.getAirline().getAirlineNumber());
        final ZonedDateTime now = ZonedDateTime.now();
        final ZonedDateTime flightDepartureDate = flight.getDepartureDate().withHour(now.getHour())
                .withMinute(now.getMinute()).withSecond(now.getSecond()).withNano(now.getNano());

        if (now.plusDays(30).isBefore(flightDepartureDate)) {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(priceMultiplierMostDistantDates));
        } else if (now.plusDays(16).isBefore(flightDepartureDate) || now.plusDays(16).isEqual(flightDepartureDate)) {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(priceMultiplierCommonDates));
        } else if (now.plusDays(3).isBefore(flightDepartureDate) || now.plusDays(3).isEqual(flightDepartureDate)) {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(priceMultiplierSecondNearestDates));
        } else {
            flight.setBasePrice(flight.getAirline().getPrice().multiply(priceMultiplierNearestDates));
        }
    }

    public void setPriceMultiplierCommonDates(final BigDecimal priceMultiplierCommonDates) {
        this.priceMultiplierCommonDates = priceMultiplierCommonDates;
    }

    public void setPriceMultiplierNearestDates(final BigDecimal priceMultiplierNearestDates) {
        this.priceMultiplierNearestDates = priceMultiplierNearestDates;
    }

    public void setPriceMultiplierSecondNearestDates(final BigDecimal priceMultiplierSecondNearestDates) {
        this.priceMultiplierSecondNearestDates = priceMultiplierSecondNearestDates;
    }

    public void setPriceMultiplierMostDistantDates(final BigDecimal priceMultiplierMostDistantDates) {
        this.priceMultiplierMostDistantDates = priceMultiplierMostDistantDates;
    }

}
