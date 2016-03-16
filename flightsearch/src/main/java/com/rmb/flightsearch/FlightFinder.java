package com.rmb.flightsearch;

import java.util.Set;
import java.util.stream.Collectors;

import com.rmb.flightsearch.io.AirlineFactory;
import com.rmb.flightsearch.rules.FlightSearchRule;
import com.rmb.flightsearch.rules.RulesFactory;

public class FlightFinder {

    private static AirlineFactory airlinefactory = new AirlineFactory();
    private static RulesFactory rulesFactory = new RulesFactory();

    /**
     * Locates the flights for the given criteria.
     *
     * @param flightRequest
     * @return
     */
    public Set<Flight> locateFlightsFor(final FlightSearchCriteria flightRequest) {

        return locateAirlinesFor(flightRequest).stream()
                .map(airline -> createNewFlightFromAirlineAndFlightSearchCriteria(flightRequest, airline))
                .collect(Collectors.toSet());
    }

    private Flight createNewFlightFromAirlineAndFlightSearchCriteria(final FlightSearchCriteria flightRequest,
            final Airline airline) {
        final FlightSearchRule rules = rulesFactory.getRulesFor(flightRequest);

        return new Flight(airline, flightRequest.getDepartureDate(), flightRequest.getAdults(),
                flightRequest.getChildren(), flightRequest.getInfants(), rules);
    }

    private Set<Airline> locateAirlinesFor(final FlightSearchCriteria flightRequest) {

        return airlinefactory.getAirlines().stream()
                .filter(airline -> isAirlineSameRouteAsFlightRequest(flightRequest, airline))
                .collect(Collectors.toSet());
    }

    private boolean isAirlineSameRouteAsFlightRequest(final FlightSearchCriteria flightRequest, final Airline airline) {

        return airlineAndFlightRequestAreOfEqualOrigin(flightRequest, airline)
                && airlineAndFlightRequestAreOfEqualDestination(flightRequest, airline);
    }

    private boolean airlineAndFlightRequestAreOfEqualDestination(final FlightSearchCriteria flightRequest,
            final Airline airline) {

        return airline.getDestination().equals(flightRequest.getDestinationIATA());
    }

    private boolean airlineAndFlightRequestAreOfEqualOrigin(final FlightSearchCriteria flightRequest,
            final Airline airline) {

        return airline.getOrigin().equals(flightRequest.getOriginIATA());
    }

}
