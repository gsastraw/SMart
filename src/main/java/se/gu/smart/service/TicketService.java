package se.gu.smart.service;

import se.gu.smart.model.Ticket;
import se.gu.smart.repository.Repositories;
import se.gu.smart.repository.TicketRepository;

public final class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService() {
        this.ticketRepository = Repositories.getTicketRepository();
    }

    public void listTickets(Ticket.Status status) {
        ticketRepository.getTickets().stream().filter(ticket -> ticket.getStatus().equals(status))
                .forEach(ticket -> System.out.println(ticket));
    }
}

