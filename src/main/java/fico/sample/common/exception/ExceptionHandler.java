package fico.sample.common.exception;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Inject
    javax.inject.Provider<ContainerRequestContext> containerRequestContextProvider;
    @Override
    public Response toResponse(Exception e) {
        return null;
    }

    private Response mapExceptionToResponse(Exception exception) {
        log.error(exception.getMessage());
        ExceptionResponse body;
        if (exception instanceof NotFoundException) {
            body = new ExceptionResponse(
                    "Error",
                    "Not Found",
                    "---"
            );

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(body)
                    .build();

        }
        //-----------------
        body = new ExceptionResponse(
                "Error",
                "Internal Server",
                exception.getMessage()
        );
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(body)
                .build();
    }
}
