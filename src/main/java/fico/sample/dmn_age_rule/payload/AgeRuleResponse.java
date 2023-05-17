package fico.sample.dmn_age_rule.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import fico.sample.dmn_age_rule.model.AgeRuleOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgeRuleResponse {
    private String status;
    @JsonProperty("error-code")
    private String errorCode;
    @JsonProperty("error-message")
    private String errorMessage;
    @JsonProperty("rule-output")
    private AgeRuleOutput ruleOutput;
}
