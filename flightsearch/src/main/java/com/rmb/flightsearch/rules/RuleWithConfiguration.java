package com.rmb.flightsearch.rules;

import java.util.function.Consumer;

public abstract class RuleWithConfiguration implements FlightSearchRule {

    private Consumer<FlightSearchRule> ruleConfig;

    public Consumer<FlightSearchRule> getRuleConfig() {
        return ruleConfig;
    }

    public void setRuleConfig(final Consumer<FlightSearchRule> ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

    @Override
    public void prepare() {
        if (getRuleConfig() != null) {
            getRuleConfig().accept(this);
        }
    }
}
