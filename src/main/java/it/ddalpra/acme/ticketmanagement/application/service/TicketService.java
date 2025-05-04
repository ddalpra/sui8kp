package it.ddalpra.acme.ticketmanagement.application.service;

import java.time.LocalDateTime;
import java.util.List;

import it.ddalpra.acme.ticketmanagement.application.port.in.TicketServicePort;
import it.ddalpra.acme.ticketmanagement.application.port.out.TicketPersistencePort;
import it.ddalpra.acme.ticketmanagement.domain.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TicketService implements TicketServicePort {

    @Inject
    TicketPersistencePort ticketPersistencePort;

    @Override
    public Ticket createTicket(Ticket ticket) {
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setUpdateDate(LocalDateTime.now());
        return ticketPersistencePort.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketPersistencePort.findById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketPersistencePort.findAll();
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = ticketPersistencePort.findById(id);
        if (existingTicket == null) {
            // Potrebbe essere lanciata un'eccezione specifica
            return null;
        }
        existingTicket.setTitle(ticket.getTitle());
        existingTicket.setDescription(ticket.getDescription());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setUpdateDate(LocalDateTime.now());
        return ticketPersistencePort.update(existingTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketPersistencePort.deleteById(id);
    }
}