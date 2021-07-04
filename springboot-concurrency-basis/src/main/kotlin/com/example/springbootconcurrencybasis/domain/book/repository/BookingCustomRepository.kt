package com.example.springbootconcurrencybasis.domain.book.repository

import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.model.QBooking.booking
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class BookingCustomRepository: QuerydslRepositorySupport(Booking::class.java) {

    fun findAllBy(concertId: Long, ticketId: Long): List<Booking> {
        return from(booking)
            .where(booking.concert.id.eq(concertId))
            .where(booking.ticket.id.eq(ticketId))
            .fetch()
    }

    fun findOneById(id: Long): List<Booking> {
        return from(booking)
            .where(booking.id.eq(id))
            .fetch()
    }
}