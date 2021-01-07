package se.gu.smart.repository;

import se.gu.smart.model.Ticket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TicketRepository {

    private final Set<Ticket> tickets = new HashSet<>();

    protected TicketRepository() {
    }

    public Ticket createTicket(String title, String description, UUID createdBy) {
        Ticket ticket = new Ticket(title, description, createdBy);
        tickets.add(ticket);
        return ticket;
    }

    public Optional<Ticket> getTicket(int id) {
        return tickets.stream().filter(ticket -> ticket.getTicketId() == (id)).findAny();
    }

    public Set<Ticket> getTickets() {
        return Collections.unmodifiableSet(tickets);
    }

    public boolean removeTicket(int id) {
        return tickets.removeIf(ticket -> ticket.getTicketId() == (id));
    }
}
