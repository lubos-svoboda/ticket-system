package cz.lubos.ticketsystem.service;

import cz.lubos.ticketsystem.model.Ticket;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceInMemoryImpl implements TicketService {

    private ConcurrentLinkedQueue<Ticket> queue = new ConcurrentLinkedQueue<>();
    private AtomicLong sequence = new AtomicLong();

    @Override
    public Ticket generateNewTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(sequence.incrementAndGet());
        ticket.setCreateDateTime(LocalDateTime.now());
        queue.add(ticket);
        ticket.setPosition(queue.size());
        return ticket;
    }

    @Override
    public Optional<Ticket> readCurrentTicket() {
        Ticket ticket = queue.peek();
        if (ticket == null) {
            return Optional.empty();
        }
        ticket.setPosition(1);
        return Optional.of(ticket);
    }

    @Override
    public Boolean removeCurrentTicket() {
        Ticket ticket = queue.poll();
        return ticket == null;
    }
}
