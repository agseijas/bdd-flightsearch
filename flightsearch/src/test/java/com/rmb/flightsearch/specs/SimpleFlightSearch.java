package com.rmb.flightsearch.specs;

import java.math.BigDecimal;
import java.util.Set;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import com.rmb.flightsearch.Flight;
import com.rmb.flightsearch.FlightFinder;
import com.rmb.flightsearch.FlightSearchCriteria;
import com.rmb.flightsearch.Logger;
import com.rmb.flightsearch.specs.support.FlightSearchRequestBuilder;
import com.rmb.flightsearch.specs.support.ResultOfFlightSearch;

@RunWith(ConcordionRunner.class)
public class SimpleFlightSearch {

    public ResultOfFlightSearch searchFlight(final String originCity, final String destinationCity,
            final String daysFromNowToDeparture, final String passengers) {

        final FlightSearchCriteria flightRequest = new FlightSearchRequestBuilder()
                .origin(originCity)
                .destination(destinationCity)
                .departure(daysFromNowToDeparture)
                .passengers(passengers)
                .build();

        Logger.report(flightRequest);

        return performSearchFor(flightRequest);
    }

    private ResultOfFlightSearch performSearchFor(final FlightSearchCriteria flightRequest) {

        final Set<Flight> flights = FlightFinder.locateFlightsFor(flightRequest);

        final ResultOfFlightSearch results = new ResultOfFlightSearch(flights);

        return results;
    }



    public boolean hasThisAirLineWithThePriceIn(final ResultOfFlightSearch resultsOfSearch, final String flightDescription) {

        final String[] flightDataToAssert = flightDescription.split(" for ");

        if(flightDataToAssert != null && flightDataToAssert.length == 2){
            final String airlineNumber = flightDataToAssert[0].trim();
            final BigDecimal price = new BigDecimal(flightDataToAssert[1].replace("€", "").trim());

            for(final Flight flight : resultsOfSearch.getFlights()){
                if(flight.getAirline().equals(airlineNumber) && flight.getTotalPrice().compareTo(price) == 0){
                    return true;
                }
            }
        }
        return false;
    }

}
