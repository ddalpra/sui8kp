package it.ddalpra.acme.ticketmanagement.application.service;

import java.time.LocalDateTime;
import java.util.List;

import it.ddalpra.acme.ticketmanagement.application.port.in.TicketServicePort;
import it.ddalpra.acme.ticketmanagement.application.port.out.TicketPersistencePort;
import it.ddalpra.acme.ticketmanagement.domain.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class TicketService implements TicketServicePort {

    @Inject
    TicketPersistencePort ticketPersistencePort;

    @Inject
    AuditLogService auditLogService;

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setUpdateDate(LocalDateTime.now());
        Ticket createdTicket = ticketPersistencePort.save(ticket);
        auditLogService.logTicketCreated(createdTicket.getId(), "Ticket creato con titolo: " + createdTicket.getTitle());
        return createdTicket;
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketPersistencePort.findById(id);
    }

    @Override
    public List<Ticket> getAllTickets(int page, int size, Ticket.TicketStatus status) {
        return ticketPersistencePort.findAll(page, size, status);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = ticketPersistencePort.findById(id);
        if (existingTicket == null) {
            return null;
        }
        String oldDetails = String.format("Ticket ID %d: Titolo='%s', Descrizione='%s', Stato='%s'",
                                        id, existingTicket.getTitle(), existingTicket.getDescription(), existingTicket.getStatus());
        existingTicket.setTitle(ticket.getTitle());
        existingTicket.setDescription(ticket.getDescription());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setUpdateDate(LocalDateTime.now());
        Ticket updatedTicket = ticketPersistencePort.update(existingTicket);
        String newDetails = String.format("Ticket ID %d: Titolo='%s', Descrizione='%s', Stato='%s'",
                                        id, updatedTicket.getTitle(), updatedTicket.getDescription(), updatedTicket.getStatus());
        auditLogService.logTicketUpdated(id, "Dettagli precedenti: " + oldDetails + " | Nuovi dettagli: " + newDetails);
        return updatedTicket;
    }

    @Override
    @Transactional // Assicurati che l'audit log sia nella stessa transazione dell'eliminazione
    public void deleteTicket(Long id) {
        Ticket existingTicket = ticketPersistencePort.findById(id);
        if (existingTicket != null) {
            ticketPersistencePort.deleteById(id);
            auditLogService.logTicketDeleted(id);
        }
        
    }
}