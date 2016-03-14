package com.rmb.flightsearch;

import java.util.Set;
import java.util.stream.Collectors;

import com.rmb.flightsearch.rules.PassengerTypeRule;

public class FlightFinder {

    public static AirlineFactory factory = new AirlineFactory();

    public static Set<Flight> locateFlightsFor(final FlightSearchCriteria flightRequest) {

        return locateAirlinesFor(flightRequest).stream()
                .map(airline -> createNewflightFromAirlineAndFlightSearchCriteria(flightRequest, airline))
                .collect(Collectors.toSet());
    }

    private static Flight createNewflightFromAirlineAndFlightSearchCriteria(final FlightSearchCriteria flightRequest,
            final Airline airline) {
        final PassengerTypeRule rules = new PassengerTypeRule();
        return new Flight(airline, flightRequest.getDepartureDate(), flightRequest.getAdults(), flightRequest.getChildren(), flightRequest.getInfants(), rules);
    }

    private static Set<Airline> locateAirlinesFor(final FlightSearchCriteria flightRequest) {

        return factory.getAirlines().stream()
                .filter(airline -> airlineAndFlightRequestAreOfEqualOrigin(flightRequest, airline)
                        && airlineAndFlightRequestAreOfEqualDestination(flightRequest, airline))
                .collect(Collectors.toSet());
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
