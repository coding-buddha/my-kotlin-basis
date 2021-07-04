package com.example.springbootconcurrencybasis

import com.example.springbootconcurrencybasis.domain.conert.api.ConcertController
import com.example.springbootconcurrencybasis.domain.conert.api.resource.ConcertCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.ticket.api.TicketController
import com.example.springbootconcurrencybasis.domain.ticket.api.resources.TicketCreateResource
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import org.springframework.beans.factory.annotation.Autowired

class TestHelper : IntegrationSupport() {

    @Autowired
    private lateinit var ticketController: TicketController

    @Autowired
    private lateinit var concertController: ConcertController

    /**
     * 콘서트를 생성한다.
     */
    fun createConcert(concertCreateResource: ConcertCreateResource): Concert {
        return concertController.create(concertCreateResource).body!!
    }

    /**
     * 티켓을 생성한다.
     */
    fun createTicket(concertId: Long, ticketCreateResource: TicketCreateResource): Ticket {
        return ticketController.create(concertId, ticketCreateResource).body!!
    }
}