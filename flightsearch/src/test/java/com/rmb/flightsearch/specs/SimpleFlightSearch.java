package com.rmb.flightsearch.specs;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        Logger.reportFlightSearch(flightRequest);

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
            final BigDecimal price = new BigDecimal(flightDataToAssert[1].replace("EUR", "").trim());

            for(final Flight flight : resultsOfSearch.getFlights()){
                if(flight.getAirline().getAirlineNumber().equals(airlineNumber) ){
                    Logger.report("Found flight: " + airlineNumber);
                    final BigDecimal scaledPrice = flightPricewithJustTwoDecimalsRoundingHalfUp(flight);
                    if(scaledPrice.compareTo(price) == 0){
                        Logger.report("Found flight (" + airlineNumber + ") price: " + price);
                        return true;
                    } else {
                        Logger.report("Flight price " + scaledPrice + " doesn't match expected price: " + price);
                    }
                }
            }
        }
        return false;
    }

    private BigDecimal flightPricewithJustTwoDecimalsRoundingHalfUp(final Flight flight) {
        return flight.getTotalPrice().setScale(2, RoundingMode.HALF_UP);
    }

    public String countLocatedFlights(final ResultOfFlightSearch resultsOfSearch){
        return Integer.toString(resultsOfSearch.getFlights().size());
    }

    public String hasNoFlights(final ResultOfFlightSearch resultsOfSearch, final String noFoundFlightMessage){
        final int foundFlightsCount = resultsOfSearch.getFlights().size();

        if(foundFlightsCount == 0){
            return noFoundFlightMessage;
        }
        return "Some flights were found.";
    }

}
