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

    fun findOneByIdOrThrow(id: Long) : Ticket {
        return ticketRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Ticket[$id] 를 찾을 수 없습니다.")
    }
}