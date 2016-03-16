package com.rmb.flightsearch.rules;

import java.math.BigDecimal;

import com.rmb.flightsearch.Flight;
import com.rmb.flightsearch.Logger;

public class PassengerTypeRule extends RuleWithConfiguration {

    BigDecimal discountPriceForChildren;

    @Override
    public void apply(final Flight flight) {
        Logger.report("Applied rule(" + this.getClass().getName() + ") for flight: "
                + flight.getAirline().getAirlineNumber());

        if (flight.getChildren() > 0) {
            flight.setChildrenPrice(flight.getBasePrice().multiply(discountPriceForChildren));
        }
        if (flight.getInfants() > 0) {
            final BigDecimal infantPrice = flight.getAirline().getInfantPrice();
            if (infantPrice != null) {
                flight.setInfantsPrice(infantPrice);
            }
        }
    }

    public void setDiscountPriceForChildren(final BigDecimal discountPriceForChildren) {
        this.discountPriceForChildren = discountPriceForChildren;
    }
}
