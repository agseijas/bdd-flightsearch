package com.rmb.flightsearch.specs.support;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rmb.flightsearch.FlightSearchCriteria;

public class FlightSearchRequestBuilder {

    private FlightSearchCriteria request = new FlightSearchCriteria();
    private static Map<String, String> cities = new HashMap<>();

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
     * Ex: If we're at 01/01/2016 and we pass the string "30" then adds 30 days and sets on the request to build the date 31/01/2016.
     * @param daysFromNow in the format "X days"
     * @return
     */
    public FlightSearchRequestBuilder departure(final String daysFromNow) {
        final long days = Long.parseLong(daysFromNow);
        final ZonedDateTime departureDate = ZonedDateTime.now().plus(days, ChronoUnit.DAYS);
        request.setDepartureDate(departureDate);
        return this;
    }

    public FlightSearchRequestBuilder passengers(final String passengers) {
        final String adult = "adult";
        final String child = "child";
        final String infant = "infant";

        for(final String passengerNumberAndType : preparePassengersTypes(passengers)){
            if(passengerNumberAndType.contains(adult)){
                request.setAdults(Integer.parseInt(getNumberOfPassengerForType(adult, passengerNumberAndType)));
            } else if(passengerNumberAndType.contains(child)){
                request.setChildren(Integer.parseInt(getNumberOfPassengerForType(child, passengerNumberAndType)));
            } else if(passengerNumberAndType.contains(infant)){
                request.setInfants(Integer.parseInt(getNumberOfPassengerForType(infant, passengerNumberAndType)));
            }
        }
        return this;
    }

    private String[] preparePassengersTypes(final String passengers) {
        return passengers.replace(" and ", ", ").split(",");
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

        if(cities.isEmpty()) {
            loadCities();
        }
        if(cities.containsKey(cityName)){
            return cities.get(cityName);
        }

        return "";
    }

    private synchronized void loadCities() {
        try (final Stream<String> streamCities = getFilesLineStreamFrom("/cities.csv")) {

            cities = streamCities
                    .map(cityData -> cityData.split(","))
                    .collect(
                            Collectors.toMap(cityData -> cityData[1], cityData -> cityData[0])
                            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Unable to load cities");
        }
    }

    private Stream<String> getFilesLineStreamFrom(final String filePath) throws IOException, URISyntaxException {
        return Files.lines(Paths.get(getUriForFilePathResource(filePath)));
    }

    private URI getUriForFilePathResource(final String filePath) throws URISyntaxException {
        return getClass().getResource(filePath).toURI();
    }
}
