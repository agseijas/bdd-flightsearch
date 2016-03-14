package com.rmb.flightsearch;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.rmb.flightsearch.rules.PassengerPriceRule;

public class Flight{

    private final Airline airline;
    private final int adults;
    private final int children;
    private final int infants;
    private final ZonedDateTime departureDate;

    private BigDecimal basePrice;
    private BigDecimal childrenPrice;
    private BigDecimal infantsPrice;

    private final PassengerPriceRule rules;

    public Flight(final Airline airline, final ZonedDateTime departureDate, final int adults, final int children, final int infants, final PassengerPriceRule rules) {
        this.airline = airline;
        this.departureDate = departureDate;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.rules = rules;

        basePrice = new BigDecimal(0);
        childrenPrice = new BigDecimal(0);
        infantsPrice = new BigDecimal(0);
    }

    public Airline getAirline() {
        return airline;
    }

    public int getAdults() {
        return adults;
    }

    public int getChildren() {
        return children;
    }

    public int getInfants() {
        return infants;
    }

    public ZonedDateTime getDepartureDate() {
        return departureDate;
    }

    public void setBasePrice(final BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void setChildrenPrice(final BigDecimal childrenPrice) {
        this.childrenPrice = childrenPrice;
    }

    public void setInfantsPrice(final BigDecimal infantsPrice) {
        this.infantsPrice = infantsPrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getChildrenPrice() {
        return childrenPrice;
    }

    public BigDecimal getInfantsPrice() {
        return infantsPrice;
    }

    /**
     * Makes the flight total price calculation by applying the rules given to this flight and by its passengers.
     * @return
     */
    public BigDecimal getTotalPrice() {
        rules.apply(this);
        final BigDecimal adultsPricePerAdult = basePrice.multiply(new BigDecimal(getAdults()));
        final BigDecimal childrenPricePerAdult = childrenPrice.multiply(new BigDecimal(getChildren()));
        final BigDecimal infantsPricePerAdult = infantsPrice.multiply(new BigDecimal(getInfants()));
        return adultsPricePerAdult.add(childrenPricePerAdult).add(infantsPricePerAdult);
    }

}
