package it.ddalpra.acme.ticketmanagement.adapter.in.rest;

import java.util.List;

import it.ddalpra.acme.ticketmanagement.application.port.in.TicketServicePort;
import it.ddalpra.acme.ticketmanagement.domain.Ticket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {

    @Inject
    TicketServicePort ticketService;

    @POST
    public Response createTicket(Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);
        return Response.status(Response.Status.CREATED).entity(createdTicket).build();
    }

    @GET
    @Path("/{id}")
    public Response getTicketById(@PathParam("id") Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket != null) {
            return Response.ok(ticket).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return Response.ok(tickets).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTicket(@PathParam("id") Long id, Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        if (updatedTicket != null) {
            return Response.ok(updatedTicket).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") Long id) {
        ticketService.deleteTicket(id);
        return Response.noContent().build();
    }
}