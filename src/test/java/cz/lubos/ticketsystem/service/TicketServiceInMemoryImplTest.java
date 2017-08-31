package cz.lubos.ticketsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cz.lubos.ticketsystem.model.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TicketServiceInMemoryImplTest {

    TicketServiceInMemoryImpl ticketService;

    @Before
    public void setup() {
        this.ticketService = new TicketServiceInMemoryImpl();
    }

    @Test
    public void shouldUseCorrectOrderingWhenNewTicketGenerated() {
        Ticket ticket1 = ticketService.generateNewTicket();
        Ticket ticket2 = ticketService.generateNewTicket();

        assertTrue(ticket1.getId() < ticket2.getId());
        assertTrue(ticket1.getPosition() < ticket2.getPosition());
    }

    @Test
    public void shouldReadFirstTicketWhenMoreTicketGenerated() {
        Ticket ticket1 = ticketService.generateNewTicket();
        ticketService.generateNewTicket();
        ticketService.generateNewTicket();

        Ticket currentTicket = ticketService.readCurrentTicket().get();

        assertEquals(ticket1, currentTicket);
    }

    @Test
    public void shouldReadSecondTicketWhenFirstTicketRemoved() {
        ticketService.generateNewTicket();
        Ticket ticket2 = ticketService.generateNewTicket();
        ticketService.generateNewTicket();

        ticketService.removeCurrentTicket();
        Ticket currentTicket = ticketService.readCurrentTicket().get();

        assertEquals(ticket2, currentTicket);
    }

    // TODO other tests not implemented due to time limit
}
