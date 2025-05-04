package it.ddalpra.acme.ticketmanagement.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import it.ddalpra.acme.ticketmanagement.adapter.out.persistence.AuditLogRepository;
import it.ddalpra.acme.ticketmanagement.domain.AuditLog;

@ApplicationScoped
public class AuditLogService {

    @Inject
    AuditLogRepository auditLogRepository;

    public void logTicketCreated(Long ticketId, String details) {
        auditLogRepository.log(new AuditLog("CREATE", ticketId, details));
    }

    public void logTicketUpdated(Long ticketId, String details) {
        auditLogRepository.log(new AuditLog("UPDATE", ticketId, details));
    }

    public void logTicketDeleted(Long ticketId) {
        auditLogRepository.log(new AuditLog("DELETE", ticketId, null));
    }
}