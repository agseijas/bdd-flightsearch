package com.rmb.flightsearch;

import java.util.Set;
import java.util.stream.Collectors;

import com.rmb.flightsearch.io.AirlineFactory;
import com.rmb.flightsearch.rules.PassengerTypesRule;

public class FlightFinder {

    private static AirlineFactory factory = new AirlineFactory();

    /**
     * Locates the flights for the given criteria.
     * @param flightRequest
     * @return
     */
    public static Set<Flight> locateFlightsFor(final FlightSearchCriteria flightRequest) {

        return locateAirlinesFor(flightRequest).stream()
                .map(airline -> createNewFlightFromAirlineAndFlightSearchCriteria(flightRequest, airline))
                .collect(Collectors.toSet());
    }

    private static Flight createNewFlightFromAirlineAndFlightSearchCriteria(final FlightSearchCriteria flightRequest,
            final Airline airline) {
        final PassengerTypesRule rules = new PassengerTypesRule();
        return new Flight(airline, flightRequest.getDepartureDate(), flightRequest.getAdults(), flightRequest.getChildren(), flightRequest.getInfants(), rules);
    }

    private static Set<Airline> locateAirlinesFor(final FlightSearchCriteria flightRequest) {

        return factory.getAirlines().stream()
                .filter(airline -> isAirlineSameRouteAsFlightRequest(flightRequest, airline))
                .collect(Collectors.toSet());
    }

    private static boolean isAirlineSameRouteAsFlightRequest(final FlightSearchCriteria flightRequest,
            Airline airline) {
        return airlineAndFlightRequestAreOfEqualOrigin(flightRequest, airline)
                && airlineAndFlightRequestAreOfEqualDestination(flightRequest, airline);
    }

    private static boolean airlineAndFlightRequestAreOfEqualDestination(final FlightSearchCriteria flightRequest,
            final Airline airline) {
        return airline.getDestination().equals(flightRequest.getDestinationIATA());
    }

    private static boolean airlineAndFlightRequestAreOfEqualOrigin(final FlightSearchCriteria flightRequest,
            final Airline airline) {
        return airline.getOrigin().equals(flightRequest.getOriginIATA());
    }

}
