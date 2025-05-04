package it.ddalpra.acme.ticketmanagement.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import it.ddalpra.acme.ticketmanagement.domain.Ticket;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public TicketEntity() {
    }

    public TicketEntity(Long id, String title, String description, String status, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    // Metodo di conversione da Entity a Domain
    public static Ticket toDomain(TicketEntity entity) {
        if (entity == null) {
            return null;
        }
        Ticket ticket = new Ticket(entity.getTitle(), entity.getDescription());
        ticket.setId(entity.getId());
        ticket.setStatus(entity.getStatus());
        ticket.setCreationDate(entity.getCreationDate());
        ticket.setUpdateDate(entity.getUpdateDate());
        return ticket;
    }

    // Metodo di conversione da Domain a Entity
    public static TicketEntity fromDomain(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return new TicketEntity(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreationDate(),
                ticket.getUpdateDate()
        );
    }
}