package com.rmb.flightsearch;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Flight{

    private final Airline airline;
    private final int adults;
    private final int children;
    private final int infants;
    private final ZonedDateTime departureDate;

    public Flight(final Airline airline, final ZonedDateTime departureDate, final int adults, final int children, final int infants) {
        this.airline = airline;
        this.departureDate = departureDate;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
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

    public BigDecimal getTotalPrice() {
        return airline.getPrice();
    }

}
