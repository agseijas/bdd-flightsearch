package com.rmb.flightsearch.rules;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import com.rmb.flightsearch.FlightSearchCriteria;

public class RulesFactory {

    private static Map<Class<? extends FlightSearchRule>, Consumer<FlightSearchRule>> flightSearchRulesConfig;

    /**
     * Gets the rules applicable for the flight search criteria.
     *
     *
     * @param searchCriteria
     * @return
     */
    public FlightSearchRule getRulesFor(final FlightSearchCriteria searchCriteria) {

        final PriceRules rule = new PriceRules(getFlightSearchRulesConfig());

        rule.prepare();

        return rule;
    }

    private Map<Class<? extends FlightSearchRule>, Consumer<FlightSearchRule>> getFlightSearchRulesConfig() {
        if (flightSearchRulesConfig == null) {
            flightSearchRulesConfig = createFlightSearchRulesConfig();
        }
        return flightSearchRulesConfig;
    }

    private Map<Class<? extends FlightSearchRule>, Consumer<FlightSearchRule>> createFlightSearchRulesConfig() {
        final Map<Class<? extends FlightSearchRule>, Consumer<FlightSearchRule>> flightSearchRulesConfig = new ConcurrentHashMap<>();

        flightSearchRulesConfig.put(PassengerTypeRule.class, (final FlightSearchRule rules) -> {
            final PassengerTypeRule rule = (PassengerTypeRule) rules;
            rule.setDiscountPriceForChildren(new BigDecimal("0.67"));
        });
        flightSearchRulesConfig.put(DaysToDepartureRule.class, (final FlightSearchRule rules) -> {
            final DaysToDepartureRule rule = (DaysToDepartureRule) rules;
            rule.setPriceMultiplierMostDistantDates(new BigDecimal("0.8"));
            rule.setPriceMultiplierCommonDates(new BigDecimal("1"));
            rule.setPriceMultiplierSecondNearestDates(new BigDecimal("1.2"));
            rule.setPriceMultiplierNearestDates(new BigDecimal("1.5"));
        });
        return flightSearchRulesConfig;
    }

}
