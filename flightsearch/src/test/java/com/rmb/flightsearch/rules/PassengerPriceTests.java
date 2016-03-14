package com.rmb.flightsearch.rules;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.rmb.flightsearch.Flight;
import com.rmb.flightsearch.TestUtils;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

public class PassengerPriceTests {

    @Test
    public void flightTakenMoreThan30Days_ForOneAdult_TotalPrice(){
        final ZonedDateTime now = ZonedDateTime.now().plus(30, ChronoUnit.DAYS);
        final PassengerTypeRule ruleDays = new PassengerTypeRule();
        final Flight flightWithOneAdult = new Flight(TestUtils.VALID_AIRLINE, now, 1, 0, 0, ruleDays);

        final BigDecimal priceForMoreThan30days80Percent = TestUtils.VALID_AIRLINE.getPrice().multiply(new BigDecimal("0.8"));

        assertThat("flightHasTheSamePriceForAdult", flightWithOneAdult.getTotalPrice(), equalTo(priceForMoreThan30days80Percent));
    }
}
