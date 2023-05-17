package fico.sample.dmn_age_rule.service;

import fico.sample.dmn_age_rule.model.AgeRuleInput;
import fico.sample.dmn_age_rule.model.AgeRuleOutput;

public interface AgeRuleService {
    AgeRuleOutput executeRule(AgeRuleInput input);
}
