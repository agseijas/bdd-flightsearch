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
        final ZonedDateTime now = ZonedDateTime.now().plus(31, ChronoUnit.DAYS);
        final PassengerTypesRule ruleDays = new PassengerTypesRule();
        final Flight flightWithOneAdult = new Flight(TestUtils.VALID_AIRLINE, now, 1, 0, 0, ruleDays);

        final BigDecimal priceForMoreThan30days_80Percent = TestUtils.VALID_AIRLINE.getPrice().multiply(new BigDecimal("0.8"));

        assertThat("flight price is 80% of base price", flightWithOneAdult.getTotalPrice(), equalTo(priceForMoreThan30days_80Percent));
    }

    @Test
    public void flightTakenMoreOrEqualThan16AndLessOrEqualThan30days_ForOneAdult_TotalPrice(){
        final ZonedDateTime now = ZonedDateTime.now().plus(16, ChronoUnit.DAYS);
        final PassengerTypesRule ruleDays = new PassengerTypesRule();
        final Flight flightWithOneAdult = new Flight(TestUtils.VALID_AIRLINE, now, 1, 0, 0, ruleDays);

        final BigDecimal priceForMoreOrEqualThan16AndLessOrEqualThan30days_100Percent = TestUtils.VALID_AIRLINE.getPrice();

        assertThat("flight price is 100% of base price", flightWithOneAdult.getTotalPrice(), equalTo(priceForMoreOrEqualThan16AndLessOrEqualThan30days_100Percent));
    }

    @Test
    public void flightTakenMoreOrEqualThan3AndLessOrEqualThan15days_ForOneAdult_TotalPrice(){
        final ZonedDateTime now = ZonedDateTime.now().plus(3, ChronoUnit.DAYS);
        final PassengerTypesRule ruleDays = new PassengerTypesRule();
        final Flight flightWithOneAdult = new Flight(TestUtils.VALID_AIRLINE, now, 1, 0, 0, ruleDays);

        final BigDecimal priceForMoreOrEqualThan3AndLessOrEqualThan15days_120Percent = TestUtils.VALID_AIRLINE.getPrice().multiply(new BigDecimal("1.2"));

        assertThat("flight price is 120% of base price", flightWithOneAdult.getTotalPrice(), equalTo(priceForMoreOrEqualThan3AndLessOrEqualThan15days_120Percent));
    }

    @Test
    public void flightTakenLessThan3days_ForOneAdult_TotalPrice(){
        final ZonedDateTime now = ZonedDateTime.now();
        final PassengerTypesRule ruleDays = new PassengerTypesRule();
        final Flight flightWithOneAdult = new Flight(TestUtils.VALID_AIRLINE, now, 1, 0, 0, ruleDays);

        final BigDecimal priceForMoreThan3AndLessThan16days_150Percent = TestUtils.VALID_AIRLINE.getPrice().multiply(new BigDecimal("1.5"));

        assertThat("flight price is 150% of base price", flightWithOneAdult.getTotalPrice(), equalTo(priceForMoreThan3AndLessThan16days_150Percent));
    }
}
