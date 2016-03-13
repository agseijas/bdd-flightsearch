package com.rmb.flightsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AirlineFactory {

    private static Set<Airline> airlines = new HashSet<>();

    protected AirlineFactory() {
    }

    public Set<Airline> getAirlines(){

        if(airlines.isEmpty()){
            loadAirlines();
        }

        return airlines;

    }

    private void loadAirlines() {
        final ReentrantLock lock = new ReentrantLock();

        try {
            lock.tryLock(5, TimeUnit.SECONDS);
            if(airlines.size() == 0){
                airlines = readAirlinesFromFile("/airlines.csv");
            }

        } catch (final InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    protected Set<Airline> readAirlinesFromFile(final String airlinesFilePath) {
        final Set<Airline> airlines = new HashSet<>();

        try {
            final InputStream streamFile = getClass().getResourceAsStream(airlinesFilePath);
            if(streamFile == null){
                throw new RuntimeException("Unable to load airlines");
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamFile));

            String airlineData = bufferedReader.readLine();
            while(hasReadNewAirlineDataFromFile(airlineData)){

                final Airline airline = createAirlineFromAirlineDataLine_InFile(airlineData);

                airlines.add(airline);

                airlineData = bufferedReader.readLine();
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return airlines;
    }

    protected Airline createAirlineFromAirlineDataLine_InFile(final String airlineDataLine) {
        final String[] airlineData = airlineDataLine.split(",");
        final Airline airline = new Airline.buildWith()
                .origin(airlineData[0]).destination(airlineData[1]).airline(airlineData[2]).price(airlineData[3]).createAirline();

        return airline;
    }

    private boolean hasReadNewAirlineDataFromFile(final String airline) {
        return airline != null;
    }

}
