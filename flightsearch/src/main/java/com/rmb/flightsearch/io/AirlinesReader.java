package com.rmb.flightsearch.io;

import java.util.Set;

import com.rmb.flightsearch.Airline;

public interface AirlinesReader {

    Set<Airline> readAirlinesFromIO(final String... params);
}
