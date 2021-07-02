package com.example.springbootconcurrencybasis.domain.conert.repository

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.model.QConcert.concert
import com.example.springbootconcurrencybasis.domain.ticket.model.QTicket.ticket
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ConcertCustomRepository(
    private val jpaQueryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Concert::class.java) {

    fun findOneWithTicketByConcertIdAndTicketId(concertId: Long, ticketId: Long): List<Concert> {
        return from(concert)
            .innerJoin(concert.tickets)
            .where(concert.id.eq(concertId))
            .where(ticket.id.eq(ticketId))
            .fetchJoin()
            .fetch()
    }
}