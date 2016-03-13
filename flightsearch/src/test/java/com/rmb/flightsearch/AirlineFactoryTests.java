package com.rmb.flightsearch;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.rmb.flightsearch.unit.IntegrationTest;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Category(value=IntegrationTest.class)
public class AirlineFactoryTests {

    @Test
    public void factoryLoadsAllAirlinesWhenAirlinesAreRequired(){
        final AirlineFactory factory = new AirlineFactory();

        assertTrue("no airlines were read", factory.getAirlines().size() > 0);
    }

    @Test
    public void readsAirlinesFromCSVTestFileCorrectly(){
        final AirlineFactory factory = new AirlineFactory();

        final Set<Airline> airlines = factory.readAirlinesFromFile("/airlines-test.csv");

        assertTrue("some airlines were read", airlines.size() > 0);

        final Airline airlineToFind = getFromAirlinesTheAirlineOfNumber("LH6620", airlines);
        assertThat("Contains airline LH6620.", airlineToFind.getAirlineNumber() , equalTo("LH6620"));
        assertThat("Contains airline LH6620 correct destination.", airlineToFind.getDestination() , equalTo("CPH"));
        assertThat("Contains airline LH6620 correct origin.", airlineToFind.getOrigin() , equalTo("LHR"));
        assertThat("Contains airline LH6620 correct price.", airlineToFind.getPrice() , equalTo(new BigDecimal("217")));
    }

    private Airline getFromAirlinesTheAirlineOfNumber(final String airlineNumber, final Set<Airline> airlines) {

        for (final Airline airline : airlines) {
            if(airlineNumber.equals(airline.getAirlineNumber())){
                return airline;
            }
        }
        return TestUtils.WRONG_AIRLINE;
    }
}
