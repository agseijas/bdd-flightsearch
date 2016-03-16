package com.rmb.flightsearch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.rmb.flightsearch.io.AirlinesCSVFileReader;
import com.rmb.flightsearch.io.AirlinesReader;
import com.rmb.flightsearch.unit.IntegrationTest;

public class AirlineReaderTests {

    @Test
    @Category(IntegrationTest.class)
    public void readsAirlinesFromCSVTestFileCorrectly() {
        final AirlinesReader reader = new AirlinesCSVFileReader();

        final Set<Airline> airlines = reader.readAirlinesFromIO("/airlines-test.csv", "/infant_prices-test.csv");

        assertTrue("some airlines were read", airlines.size() > 0);

        final Airline airlineToFind = getFromAirlinesTheAirlineOfNumber("LH6620", airlines);
        assertThat("Contains airline LH6620.", airlineToFind.getAirlineNumber(), equalTo("LH6620"));
        assertThat("Contains airline LH6620 correct destination.", airlineToFind.getDestination(), equalTo("CPH"));
        assertThat("Contains airline LH6620 correct origin.", airlineToFind.getOrigin(), equalTo("LHR"));
        assertThat("Contains airline LH6620 correct price.", airlineToFind.getPrice(), equalTo(new BigDecimal("217")));
        assertThat("Contains airline LH6620 correct infantprice.", airlineToFind.getInfantPrice(),
                equalTo(new BigDecimal("7")));
        assertThat("Contains airline LH6620 has company.", airlineToFind.getCompanyName(), equalTo("Lufthansa"));
    }

    @Test(expected = FSException.class)
    @Category(IntegrationTest.class)
    public void cantReadAirlinesFromCSVTestFileCorrectly() {
        final AirlinesReader reader = new AirlinesCSVFileReader();

        reader.readAirlinesFromIO("/airlines-nonexistant.csv", "/infant_prices-nonexistant.csv");

        Assert.fail("Should raise exception because the files couldn't be found");
    }

    private Airline getFromAirlinesTheAirlineOfNumber(final String airlineNumber, final Set<Airline> airlines) {

        for (final Airline airline : airlines) {
            if (airlineNumber.equals(airline.getAirlineNumber())) {
                return airline;
            }
        }
        return TestUtils.WRONG_AIRLINE;
    }
}
