package com.example.springbootconcurrencybasis.domain.conert.service

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertCustomRepository
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional(readOnly = true)
class ConcertFindService(
    private val concertRepository: ConcertRepository,
    private val concertCustomRepository: ConcertCustomRepository
) {

    fun findOneById(id: Long) : Concert {
        return concertRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Concert[$id] 를 찾을 수 없습니다.")
    }

//    fun findOneWithTicketByIdOrThrow(concertId: Long, ticketId: Long): Concert {
//        val concerts = concertCustomRepository.findOneWithTicketByConcertIdAndTicketId(concertId = concertId, ticketId = ticketId)
//        if (concerts.isEmpty()) {
//            throw EntityNotFoundException("Concert[$concertId] 와 Ticket[$ticketId] 를 찾을 수 없습니다.")
//        }
//        return concerts.first()
//    }
}