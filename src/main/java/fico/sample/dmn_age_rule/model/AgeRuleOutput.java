package fico.sample.dmn_age_rule.model;

import lombok.Data;

@Data
public class AgeRuleOutput extends AgeRuleInput{
    private boolean adult;
}
