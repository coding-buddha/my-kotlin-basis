package com.example.springbootconcurrencybasis.domain.ticket.api

import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import com.example.springbootconcurrencybasis.domain.ticket.service.TicketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tickets")
class TicketController(
    private val ticketService: TicketService
) {

    @PostMapping
    fun createTicket(): ResponseEntity<Ticket> {
        TODO("구현이 필요하다.")
    }
}