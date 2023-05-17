package fico.sample.dmn_age_rule.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import fico.sample.dmn_age_rule.model.AgeRuleInput;
import lombok.Data;

@Data
public class AgeRuleRequest {
    @JsonProperty("system-request")
    private String systemRequest;
    private AgeRuleInput input;
}
