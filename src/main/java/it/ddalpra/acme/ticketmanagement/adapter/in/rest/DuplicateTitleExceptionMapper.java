package it.ddalpra.acme.ticketmanagement.adapter.in.rest;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


import java.util.Collections;

import it.ddalpra.acme.ticketmanagement.adapter.in.rest.ConstraintViolationExceptionMapper.ErrorResponse;
import it.ddalpra.acme.ticketmanagement.application.exception.DuplicateTitleException;

@Provider
public class DuplicateTitleExceptionMapper implements ExceptionMapper<DuplicateTitleException> {

    @Override
    public Response toResponse(DuplicateTitleException exception) {
        return Response.status(Response.Status.CONFLICT) // 409 Conflict
                .entity(new ErrorResponse("Errore di input", Collections.singletonList(exception.getMessage())))
                .type("application/json")
                .build();
    }
}