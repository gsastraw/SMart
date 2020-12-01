package se.gu.smart.repository;

import se.gu.smart.model.Ticket;
import se.gu.smart.model.UserAccount;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TicketRepository {

    private final Set<Ticket> tickets = new HashSet<>();

    public Ticket createTicket(String title, String description, UserAccount createdBy){
        Ticket ticket = new Ticket(title, description, createdBy);
        tickets.add(ticket);
        return ticket;
    }

    public Optional<Ticket> getTicket(Integer id){
        return tickets.stream().filter(ticket -> ticket.getTicketId().equals(id)).findAny();
    }

    public Set<Ticket> getTickets(){
        return Collections.unmodifiableSet(tickets);
    }

    public boolean removeTicket(Integer id){
        return tickets.removeIf(ticket -> ticket.getTicketId().equals(id));
    }
}
