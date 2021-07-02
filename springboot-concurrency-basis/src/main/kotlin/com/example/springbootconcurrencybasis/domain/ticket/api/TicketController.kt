package com.example.springbootconcurrencybasis.domain.ticket.api

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertRepository
import com.example.springbootconcurrencybasis.domain.conert.service.ConcertFindService
import com.example.springbootconcurrencybasis.domain.ticket.api.resources.TicketCreateResource
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import com.example.springbootconcurrencybasis.domain.ticket.repository.TicketRepository
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("tickets")
class TicketController(
    private val ticketRepository: TicketRepository,
    private val concertRepository: ConcertRepository,
    private val concertFindService: ConcertFindService,
) {

    @PostMapping("concerts/{id}")
    fun create(
        @PathVariable id: Long,
        @RequestBody ticketCreateResource: TicketCreateResource
    ): ResponseEntity<Concert> {
        val concert = concertFindService.findOneById(id)
        val ticket = ticketRepository.save(Ticket.from(ticketCreateResource))
        concert.addTicket(ticket)
        concertRepository.save(concert)
        return ResponseEntity.ok(concert)
    }
}