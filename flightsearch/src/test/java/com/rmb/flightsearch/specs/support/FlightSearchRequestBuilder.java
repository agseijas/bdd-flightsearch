package com.rmb.flightsearch.specs.support;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import com.rmb.flightsearch.FlightSearchCriteria;

public class FlightSearchRequestBuilder {

    private FlightSearchCriteria request = new FlightSearchCriteria();

    public FlightSearchRequestBuilder origin(final String originCity) {
        request.setOriginIATA(locateIATACodeBy(originCity));
        return this;
    }

    public FlightSearchRequestBuilder destination(final String destinationCity) {
        request.setDestinationIATA(locateIATACodeBy(destinationCity));
        return this;
    }

    /**
     * Calculates a day from now to the specified days and sets that as the departure date for this flight search request to be built.<br/>
     * Ex: If we're at 01/01/2016 and we pass the string "30 days" then adds 30 days and sets on the request to build the date 31/01/2016.
     * @param daysFromNow in the format "X days"
     * @return
     */
    public FlightSearchRequestBuilder departure(final String daysFromNow) {
        final long days = Long.parseLong(daysFromNow.substring(0,daysFromNow.indexOf(" days")));
        final ZonedDateTime departureDate = ZonedDateTime.now().plus(days, ChronoUnit.DAYS);
        request.setDepartureDate(departureDate);
        return this;
    }

    public FlightSearchRequestBuilder passengers(final String passengers) {
        final String adult = "adult";
        final String child = "child";
        final String infant = "infant";

        for(final String passengerNumberAndType: passengers.split(",")){
            if(passengerNumberAndType.contains(adult)){
                request.setAdults(Integer.parseInt(getNumberOfPassengerForType(adult, passengerNumberAndType)));
            } else if(passengerNumberAndType.contains(child)){
                request.setChildren(Integer.parseInt(getNumberOfPassengerForType(child, passengerNumberAndType)));
            } else if(passengerNumberAndType.contains(infant)){
                request.setChildren(Integer.parseInt(getNumberOfPassengerForType(infant, passengerNumberAndType)));
            }
        }
        return this;
    }

    private String getNumberOfPassengerForType(final String adult, final String passengerType) {
        return passengerType.substring(0, passengerType.indexOf(adult)).trim();
    }

    public FlightSearchCriteria build() {
        final FlightSearchCriteria buildRequest = request;
        request = new FlightSearchCriteria();
        return buildRequest;
    }


    private String locateIATACodeBy(final String cityName) {
        switch (cityName) {
            case "Amsterdam":
                return "AMS";
            case "Frankfurt":
                return "FRA";
            default:
        }
        return "";
    }
}
