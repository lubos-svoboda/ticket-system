package cz.lubos.ticketsystem.controller;

import cz.lubos.ticketsystem.model.Ticket;
import cz.lubos.ticketsystem.service.TicketService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/ticket")
public class TicketSystemController {

    private final TicketService ticketService;

    public TicketSystemController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/current")
    public ResponseEntity readCurrentTicket() {
        Optional<Ticket> ticket = ticketService.readCurrentTicket();
        if (ticket.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ticket.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public Ticket generateNewTicket() {
        return ticketService.generateNewTicket();
    }

    @DeleteMapping("/current")
    public ResponseEntity removeCurrentTicket() {
        if (ticketService.removeCurrentTicket()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
