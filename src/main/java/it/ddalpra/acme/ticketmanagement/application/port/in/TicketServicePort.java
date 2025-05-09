package it.ddalpra.acme.ticketmanagement.application.port.in;

import java.util.List;

import it.ddalpra.acme.ticketmanagement.domain.Ticket;

public interface TicketServicePort {
    Ticket createTicket(Ticket ticket);
    Ticket getTicketById(Long id);
    List<Ticket> getAllTickets(int page, int size, Ticket.TicketStatus status);
    Ticket updateTicket(Long id, Ticket ticket);
    void deleteTicket(Long id);
}