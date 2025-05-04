package it.ddalpra.acme.ticketmanagement.adapter.out.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import it.ddalpra.acme.ticketmanagement.domain.AuditLog;

@ApplicationScoped
public class AuditLogRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void log(AuditLog auditLog) {
        em.persist(auditLog);
    }
}