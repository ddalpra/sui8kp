package it.ddalpra.acme.ticketmanagement.adapter.in.rest;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Collections;

import it.ddalpra.acme.ticketmanagement.adapter.in.rest.ConstraintViolationExceptionMapper.ErrorResponse;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        // Potremmo loggare l'eccezione qui per il debugging
        exception.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                .entity(new ErrorResponse("Errore interno del server", Collections.singletonList("Si è verificato un errore imprevisto. Si prega di riprovare più tardi.")))
                .type("application/json")
                .build();
    }
}