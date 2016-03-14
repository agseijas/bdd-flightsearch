package com.rmb.flightsearch.io;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rmb.flightsearch.Airline;

public class AirlinesCSVFileReader implements AirlinesReader{

    @Override
    public Set<Airline> readAirlinesFromIO(final String... params) {
        final String airlinesFile = params[0];
        final String infantPricesFile = params[1];
        Set<Airline> airlinesWithInfantPriceData = Collections.emptySet();

        try (final Stream<String> streamAirlines = getFilesLineStreamFrom(airlinesFile)) {
            final Set<String[]> allInfantPricesData = getInfantPriceData(infantPricesFile);
            final Set<Airline> airlines = streamAirlines
                    .map(airlineData -> createAirlineFromAirlineDataLine_InFile(airlineData, allInfantPricesData))
                    .collect(Collectors.toSet());

            airlinesWithInfantPriceData = airlines;
        } catch (final URISyntaxException | IOException e) {
            throw new RuntimeException("Unable to load airlines");
        }
        return airlinesWithInfantPriceData;
    }

    private Set<String[]> getInfantPriceData(final String infantPricesFile) throws IOException, URISyntaxException {
        Set<String[]> infantPriceData = Collections.emptySet();
        try (final Stream<String> streamInfantPrices = getFilesLineStreamFrom(infantPricesFile)) {
            infantPriceData = streamInfantPrices.map(infantPrice -> infantPrice.split(",")).collect(Collectors.toSet());
        }
        return infantPriceData;
    }

    private Stream<String> getFilesLineStreamFrom(final String filePath) throws IOException, URISyntaxException {
        return Files.lines(Paths.get(getUriForFilePathResource(filePath)));
    }

    private URI getUriForFilePathResource(final String filePath) throws URISyntaxException {
        return getClass().getResource(filePath).toURI();
    }

    private Airline createAirlineFromAirlineDataLine_InFile(final String airlineDataLine, final Set<String[]> allInfantPricesData) {
        final String[] airlineData = airlineDataLine.split(",");

        final String[] infantPriceData = allInfantPricesData.stream()
                .filter(infantPriceForCompany -> airlineIATACodeIsInfantPriceCompanyIATACode(airlineData, infantPriceForCompany))
                .findFirst()
                .orElse(null);
        if(notFound(infantPriceData)){
            return new Airline.buildWith()
                    .origin(airlineData[0])
                    .destination(airlineData[1])
                    .airline(airlineData[2])
                    .price(airlineData[3])
                    .createAirline();
        }
        return new Airline.buildWith()
                .origin(airlineData[0])
                .destination(airlineData[1])
                .airline(airlineData[2])
                .price(airlineData[3])
                .infantPrice(infantPriceData[2])
                .company(infantPriceData[1])
                .createAirline();
    }

    private boolean notFound(final String[] infantPriceData) {
        return infantPriceData == null;
    }

    private boolean airlineIATACodeIsInfantPriceCompanyIATACode(final String[] airlineData,
            final String[] infantPriceForCompany) {
        return airlineData[2].startsWith(infantPriceForCompany[0]);
    }

}
