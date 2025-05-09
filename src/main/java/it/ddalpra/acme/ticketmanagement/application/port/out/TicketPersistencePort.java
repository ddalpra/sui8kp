package it.ddalpra.acme.ticketmanagement.application.port.out;

import java.util.List;

import it.ddalpra.acme.ticketmanagement.domain.Ticket;

public interface TicketPersistencePort {
    Ticket save(Ticket ticket);
    Ticket findById(Long id);
    List<Ticket> findAll(int page, int size, Ticket.TicketStatus status);
    Ticket update(Ticket ticket);
    void deleteById(Long id);
}