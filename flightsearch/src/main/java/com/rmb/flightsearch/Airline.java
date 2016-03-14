package com.rmb.flightsearch;

import java.math.BigDecimal;

public class Airline {

    private final BigDecimal price;

    private final BigDecimal infantPrice;

    private final String airlineNumber;

    private final String companyName;

    private final String codeIATA;

    private final String destination;

    private final String origin;

    private Airline(final BigDecimal price, final BigDecimal infantPrice, final String airlineNumber, final String destination, final String origin, final String company){
        this.price = price;
        this.infantPrice = infantPrice;
        this.airlineNumber = airlineNumber;
        codeIATA = airlineNumber.substring(0,2);
        this.destination = destination;
        this.origin = origin;
        companyName = company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getInfantPrice() {
        return infantPrice;
    }

    public String getAirlineNumber() {
        return airlineNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCodeIATA() {
        return codeIATA;
    }

    public static class buildWith {

        private BigDecimal bPrice;

        private BigDecimal bInfantPrice;

        private String bAirlineNumber;

        private String bDestination;

        private String bOrigin;

        private String bCompany;

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
        public buildWith company(final String company){
            bCompany = company;
            return this;
        }
        public buildWith infantPrice(final String infantPrice) {
            bInfantPrice = new BigDecimal(infantPrice);
            return this;
        }

        public Airline createAirline(){
            return new Airline(bPrice, bInfantPrice, bAirlineNumber, bDestination, bOrigin, bCompany);
        }
    }
}
