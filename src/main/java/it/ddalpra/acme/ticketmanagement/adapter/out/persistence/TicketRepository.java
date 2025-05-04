package it.ddalpra.acme.ticketmanagement.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;

import it.ddalpra.acme.ticketmanagement.application.port.out.TicketPersistencePort;
import it.ddalpra.acme.ticketmanagement.domain.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TicketRepository implements TicketPersistencePort {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Ticket save(Ticket ticket) {
        TicketEntity entity = TicketEntity.fromDomain(ticket);
        em.persist(entity);
        return TicketEntity.toDomain(entity);
    }

    @Override
    public Ticket findById(Long id) {
        TicketEntity entity = em.find(TicketEntity.class, id);
        return TicketEntity.toDomain(entity);
    }

    @Override
    public List<Ticket> findAll(int page, int size, Ticket.TicketStatus status) {
        String jpql = "SELECT t FROM TicketEntity t";
        if (status != null) {
            jpql += " WHERE t.status = :status";
        }

        TypedQuery<TicketEntity> query = em.createQuery(jpql, TicketEntity.class);

        if (status != null) {
            query.setParameter("status", status);
        }

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList()
                .stream()
                .map(TicketEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Ticket update(Ticket ticket) {
        TicketEntity existing = em.find(TicketEntity.class, ticket.getId());
        if (existing != null) {
            existing.setTitle(ticket.getTitle());
            existing.setDescription(ticket.getDescription());
            existing.setStatus(ticket.getStatus());
            existing.setUpdateDate(java.time.LocalDateTime.now());
            em.merge(existing);
            return TicketEntity.toDomain(existing);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        TicketEntity entity = em.find(TicketEntity.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}