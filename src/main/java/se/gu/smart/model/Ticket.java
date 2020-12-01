package se.gu.smart.model;

import java.time.LocalDate;

public class Ticket {

    private static int currentId = 0;

    public enum Status {
        UNRESOLVED,
        RESOLVED
    }

    private Integer id;
    private String title;
    private String description;
    private Status status;
    private LocalDate ticketOpened;
    private LocalDate ticketClosed;
    private UserAccount createdBy;

    public Ticket(String title, String description, UserAccount createdBy) {
        this.id = currentId++;
        this.title = title;
        this.description = description;
        this.status = Status.UNRESOLVED;
        this.ticketOpened = LocalDate.now();
        this.createdBy = createdBy;
    }

    public Integer getTicketId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public Status getStatus() {
        return status;
    }

    public LocalDate getTicketOpened() {
        return ticketOpened;
    }

    public LocalDate getTicketClosed() {
        return ticketClosed;
    }

    public UserAccount getCreatedBy() {
        return createdBy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTicketClosed(LocalDate ticketClosed) {
        this.ticketClosed = ticketClosed;
    }
}
