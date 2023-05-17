package fico.sample.dmn_age_rule.service;

import fico.sample.dmn_age_rule.converter.AgeRuleConverter;
import fico.sample.dmn_age_rule.model.AgeRuleInput;
import fico.sample.dmn_age_rule.model.AgeRuleOutput;
import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.decisions.DecisionIds;
import org.kie.kogito.incubation.decisions.LocalDecisionId;
import org.kie.kogito.incubation.decisions.services.DecisionService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgeRuleServiceImpl implements AgeRuleService{
    private AppRoot appRoot;
    private DecisionService decisionService;
    private LocalDecisionId decisionId;
    public AgeRuleServiceImpl(AppRoot appRoot, DecisionService svc) {
        this.appRoot = appRoot;
        this.decisionService = svc;
        decisionId = appRoot
            .get(DecisionIds.class)
            .get("dmn.samples.person",
                    "PersonDecisions");

        DecisionIds decisionIds = appRoot
                .get(DecisionIds.class);
        LocalDecisionId localDecisionId =  decisionIds
                .get("dmn.samples.person",
                "PersonDecisions");
        //localDecisionId.services();
        //localDecisionId.
    }

    @Override
    public AgeRuleOutput executeRule(AgeRuleInput input) {
        DataContext ruleInput = AgeRuleConverter.toAgeRuleInput(input);
        DataContext ruleOutput = decisionService.evaluate(decisionId, ruleInput).data();
        return AgeRuleConverter.toAgeRuleOutput(ruleOutput);
    }
}
