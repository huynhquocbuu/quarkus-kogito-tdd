package fico.sample.common.http_log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Slf4j
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private String requestBody;
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if(responseContext.getStatus() == 200){
            log.info("\n request = {} \n response = status: {}, body: {}",
                    requestBody,
                    responseContext.getStatus(),
                    mapper.writeValueAsString(responseContext.getEntity()));
        }else{
            log.error("\n request = {} \n response = status: {}, body: {}",
                    requestBody,
                    responseContext.getStatus(),
                    mapper.writeValueAsString(responseContext.getEntity()));
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestBody = IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
        requestContext.setEntityStream(IOUtils.toInputStream(requestBody));
    }
}
