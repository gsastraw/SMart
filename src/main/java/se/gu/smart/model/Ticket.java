package se.gu.smart.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class Ticket {

    private static int currentId = 0;

    public enum Status {
        UNRESOLVED,
        RESOLVED
    }

    private int ticketId;
    private String title;
    private String description;
    private Status status;
    private LocalDate dateOpened;
    private LocalDate dateClosed;
    private UUID createdBy;

    public Ticket(String title, String description, UUID createdBy) {
        this(currentId++, title, description, Status.UNRESOLVED, LocalDate.now(), null, createdBy);
    }

    @JsonCreator
    public Ticket(
        @JsonProperty("ticketId") int ticketId,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("status") Status status,
        @JsonProperty("dateOpened") LocalDate dateOpened,
        @JsonProperty("dateClosed") LocalDate dateClosed,
        @JsonProperty("createdBy") UUID createdBy
    ) {
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateOpened = dateOpened;
        this.dateClosed = dateClosed;
        this.createdBy = createdBy;
    }

    public int getTicketId() {
        return ticketId;
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

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }

    public UUID getCreatedBy() {
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

    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dateOpened=" + dateOpened +
                ", dateClosed=" + dateClosed +
                ", createdBy=" + createdBy +
                '}';
    }
}
