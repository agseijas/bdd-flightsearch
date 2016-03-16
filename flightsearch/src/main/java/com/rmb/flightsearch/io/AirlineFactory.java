package com.rmb.flightsearch.io;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.rmb.flightsearch.Airline;
import com.rmb.flightsearch.FSException;

public class AirlineFactory {

    private static Set<Airline> airlines = new HashSet<>();

    public AirlineFactory() {
    }

    /**
     * Gets a Set of Airline from I/O and cache's the first access result.
     * 
     * @return
     */
    public Set<Airline> getAirlines() {

        if (airlines.isEmpty()) {
            loadInterlockingAirlines();
        }

        return airlines;

    }

    private void loadInterlockingAirlines() {
        final ReentrantLock lock = new ReentrantLock();

        try {
            lock.tryLock(5, TimeUnit.SECONDS);
            if (airlines.size() == 0) {
                airlines = new AirlinesCSVFileReader().readAirlinesFromIO("/airlines.csv", "/infant_prices.csv");
            }

        } catch (final InterruptedException e) {
            throw FSException.withMessage("Unable to load airlines correctly.");
        } finally {
            lock.unlock();
        }
    }

}
