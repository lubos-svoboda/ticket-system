package cz.lubos.ticketsystem.service;

import cz.lubos.ticketsystem.model.Ticket;
import java.util.Optional;

public interface TicketService {

    /**
     * Generate a new ticket and put the ticket to the end of the queue
     * @return
     */
    Ticket generateNewTicket();

    /**
     * Read current ticket or return empty when there is no ticket in the queue
     * @return
     */
    Optional<Ticket> readCurrentTicket();

    /**
     * Remove current ticket
     * @return return true if no ticket was removed from the queue (queue was empty)
     */
    Boolean removeCurrentTicket();

}
