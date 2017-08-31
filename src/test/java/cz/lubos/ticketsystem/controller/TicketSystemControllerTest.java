package cz.lubos.ticketsystem.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cz.lubos.ticketsystem.model.Ticket;
import cz.lubos.ticketsystem.service.TicketService;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TicketSystemControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    TicketService ticketService;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldGenerateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setPosition(20);
        ticket.setCreateDateTime(LocalDateTime.of(2000, 12,20, 22, 50));

        when(ticketService.generateNewTicket()).thenReturn(ticket);

        mockMvc.perform(put("/rest/ticket"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(ticket.getId().intValue())))
                .andExpect(jsonPath("$.position", is(ticket.getPosition())))
                .andExpect(jsonPath("$.createDateTime", is("2000-12-20 22:50")));
    }

    // TODO other tests not implemented due to time limit
}
