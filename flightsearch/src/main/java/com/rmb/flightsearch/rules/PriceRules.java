package com.rmb.flightsearch.rules;

import java.util.Map;
import java.util.function.Consumer;

import com.rmb.flightsearch.Flight;

public class PriceRules extends RuleWithConfiguration {

    private final DaysToDepartureRule datesRule = new DaysToDepartureRule();
    private final PassengerTypeRule typeRule = new PassengerTypeRule();

    private final Map<Class<? extends FlightSearchRule>, Consumer<FlightSearchRule>> flightSearchRulesConfig;

    public PriceRules(
            final Map<Class<? extends FlightSearchRule>, Consumer<FlightSearchRule>> flightSearchRulesConfig) {

        this.flightSearchRulesConfig = flightSearchRulesConfig;
    }

    @Override
    public void prepare() {
        super.prepare();

        datesRule.setRuleConfig(withRuleConfigOf(datesRule));
        datesRule.prepare();

        typeRule.setRuleConfig(withRuleConfigOf(typeRule));
        typeRule.prepare();
    }

    @Override
    public void apply(final Flight flight) {

        datesRule.apply(flight);
        typeRule.apply(flight);
    }

    private Consumer<FlightSearchRule> withRuleConfigOf(final FlightSearchRule rule) {
        return flightSearchRulesConfig.get(rule.getClass());
    }
}
