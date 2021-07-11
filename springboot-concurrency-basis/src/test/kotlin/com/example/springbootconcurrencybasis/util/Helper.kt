package com.example.springbootconcurrencybasis.util

import com.example.springbootconcurrencybasis.IntegrationSupport
import com.example.springbootconcurrencybasis.domain.booking.api.BookingController
import com.example.springbootconcurrencybasis.domain.booking.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.booking.model.Booking
import com.example.springbootconcurrencybasis.domain.conert.api.ConcertController
import com.example.springbootconcurrencybasis.domain.conert.api.resource.ConcertCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.ticket.api.TicketController
import com.example.springbootconcurrencybasis.domain.ticket.api.resources.TicketCreateResource
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
@Transactional
class Helper(
    private val concertController: ConcertController,
    private val ticketController: TicketController,
    private val bookingController: BookingController
) : IntegrationSupport() {

    fun createConcert(
        name: String = "아이유 2021 여름 콘서트",
        performanceDate: LocalDateTime = LocalDateTime.of(2021, 8, 1, 17, 30, 0),
        viewingTime: Long = 120,
        grade: Concert.Grade = Concert.Grade.PG_12,
    ): Concert {
        val concertCreateResource = ConcertCreateResource(
            name,
            performanceDate,
            viewingTime,
            grade
        )

        return concertController.create(concertCreateResource).body!!
    }

    fun createTicket(
        concertId: Long,
        name: String = "아이유 2021 여름 콘서트 S 좌석",
        initCount: Long = 5,
        price: Long = 50000,
        seatGrade: Ticket.SeatGrade = Ticket.SeatGrade.S_CLASS
    ): Ticket {
        val ticketCreateResource = TicketCreateResource(
            name,
            initCount,
            price,
            seatGrade
        )

        return ticketController.create(concertId, ticketCreateResource).body!!
    }

    fun createBooking(
        concertId: Long,
        ticketId: Long
    ): Booking {
        val bookingCreateResource = BookingCreateResource(
            concertId,
            ticketId
        )

        return bookingController.create(bookingCreateResource).body!!
    }
}