package fico.sample.dmn_age_rule.converter;

import fico.sample.dmn_age_rule.model.AgeRuleInput;
import fico.sample.dmn_age_rule.model.AgeRuleOutput;
import fico.sample.dmn_age_rule.payload.AgeRuleResponse;
import org.jboss.resteasy.reactive.RestResponse;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.MapDataContext;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AgeRuleConverter {
    public static DataContext toAgeRuleInput(AgeRuleInput ruleInput){
        MapDataContext dataContext = MapDataContext.create();
        dataContext.set("age", ruleInput.getAge());
        return dataContext;
    }

    public static AgeRuleOutput toAgeRuleOutput(DataContext ruleOutput){
        MapDataContext mapDataContext = ruleOutput.as(MapDataContext.class);

        AgeRuleOutput rt = new AgeRuleOutput();
        rt.setAge(mapDataContext.get("age", Integer.class));
        rt.setAdult(mapDataContext.get("adult", Boolean.class));

        return rt;
    }

    public static RestResponse<AgeRuleResponse> toRestResponseOk(AgeRuleOutput ruleOutput){
        AgeRuleResponse body = new AgeRuleResponse(
                "Success",
                "",
                "",
                ruleOutput);

        return RestResponse.ResponseBuilder
                .create(Response.Status.OK, body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static RestResponse<AgeRuleResponse> toRestResponseErr(String errorCode, String errorMessage){
        AgeRuleResponse body = new AgeRuleResponse(
                "Error",
                errorCode,
                errorMessage,
                new AgeRuleOutput());
        return RestResponse.ResponseBuilder
                .create(Response.Status.BAD_REQUEST, body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
