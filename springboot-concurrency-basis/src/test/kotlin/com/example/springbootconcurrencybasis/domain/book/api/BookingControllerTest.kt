package com.example.springbootconcurrencybasis.domain.book.api

import io.kotest.matchers.shouldBe
import com.example.springbootconcurrencybasis.TestHelper
import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRedisRepository
import com.example.springbootconcurrencybasis.domain.conert.api.resource.ConcertCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.ticket.api.resources.TicketCreateResource
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

@DisplayName("BookingController")
internal class BookingControllerTest {

    @Autowired
    private lateinit var bookingController: BookingController

    @Autowired
    private lateinit var bookingRedisRepository: BookingRedisRepository

    private val testHelper = TestHelper()

    @Test
    @DisplayName("콘서트 티켓을 예매한다.")
    fun createBookingTest() {

        // given
        val concertCreateResource = ConcertCreateResource(
            name = "아이유 콘서트 여름 2021",
            performanceDate = LocalDateTime.now(),
            viewingTime = 120,
            grade = Concert.Grade.PG_12
        )
        val concert = testHelper.createConcert(concertCreateResource)
        concert shouldNotBe null

        val ticketCreateResource = TicketCreateResource(
            name = "아이유 콘서트 여름 2021 S 좌석 스탠딩",
            initCount = 10,
            price = 50000L,
            seatGrade = Ticket.SeatGrade.S_CLASS
        )
        val ticket = testHelper.createTicket(concert.id!!, ticketCreateResource)
        ticket shouldNotBe null

        val bookingCreateResource = BookingCreateResource(
            concertId = concert.id!!,
            ticketId = ticket.id!!
        )

        // when
        val booking = bookingController.create(bookingCreateResource).body!!

        // then
        booking shouldNotBe null
    }

    @Test
    @DisplayName("콘서트 티켓을 동시에 예매한다.")
    fun createBookingConcurrencyTest() {

    }

    @Test
    @DisplayName("콘서트 티켓을 동시에 예매하고, 일부는 예매하지 못한다.")
    fun createBookingConcurrencyFailedTest() {

    }
}