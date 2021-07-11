package com.example.springbootconcurrencybasis.domain.ticket.service

import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import com.example.springbootconcurrencybasis.domain.ticket.repository.TicketRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional(readOnly = true)
class TicketFindService(
    private val ticketRepository: TicketRepository
) {
}