package com.rmb.flightsearch;

import java.math.BigDecimal;

public class Airline {

    private BigDecimal price;

    private String airlineNumber;

    private String destination;

    private String origin;

    private Airline(final BigDecimal price, final String airlineNumber, final String destination, final String origin){
        this.price = price;
        this.airlineNumber = airlineNumber;
        this.destination = destination;
        this.origin = origin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getAirlineNumber() {
        return airlineNumber;
    }

    public void setAirlineNumber(final String airlineNumber) {
        this.airlineNumber = airlineNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public static class buildWith {
        private BigDecimal bPrice;

        private String bAirlineNumber;

        private String bDestination;

        private String bOrigin;


        public buildWith price(final String price){
            bPrice = new BigDecimal(price);
            return this;
        }
        public buildWith airline(final String airline){
            bAirlineNumber = airline;
            return this;
        }
        public buildWith destination(final String destination){
            bDestination = destination;
            return this;
        }
        public buildWith origin(final String origin){
            bOrigin = origin;
            return this;
        }

        public Airline createAirline(){
            return new Airline(bPrice, bAirlineNumber, bDestination, bOrigin);
        }
    }
}
