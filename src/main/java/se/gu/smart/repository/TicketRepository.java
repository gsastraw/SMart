package se.gu.smart.repository;

import static java.util.Objects.requireNonNull;

import se.gu.smart.model.Ticket;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class TicketRepository implements Repository<Ticket> {

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

    @Override
    public Set<Ticket> getAll() {
        return Collections.unmodifiableSet(tickets);
    }

    public boolean removeTicket(int id) {
        return tickets.removeIf(ticket -> ticket.getTicketId() == (id));
    }

    @Override
    public void load(Collection<Ticket> collection) {
        requireNonNull(collection);

        tickets.addAll(collection);
    }

    @Override
    public void clear() {
        tickets.clear();
    }
}
