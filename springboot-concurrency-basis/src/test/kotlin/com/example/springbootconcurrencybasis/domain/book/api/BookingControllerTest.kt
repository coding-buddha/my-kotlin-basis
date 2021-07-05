package com.example.springbootconcurrencybasis.domain.book.api

import com.example.springbootconcurrencybasis.IntegrationSupport
import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRedisRepository
import com.example.springbootconcurrencybasis.domain.conert.api.ConcertController
import com.example.springbootconcurrencybasis.domain.conert.api.resource.ConcertCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.ticket.api.TicketController
import com.example.springbootconcurrencybasis.domain.ticket.api.resources.TicketCreateResource
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import mu.KLogging
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import javax.persistence.EntityManager

@DisplayName("BookingController 는")
internal class BookingControllerTest : IntegrationSupport() {

    @Autowired
    private lateinit var concertController: ConcertController

    @Autowired
    private lateinit var ticketController: TicketController

    @Autowired
    private lateinit var bookingController: BookingController

    @Autowired
    private lateinit var bookingRedisRepository: BookingRedisRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    companion object : KLogging()

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
        val concert = concertController.create(concertCreateResource).body!!
        concert shouldNotBe null

        val ticketCreateResource = TicketCreateResource(
            name = "아이유 콘서트 여름 2021 S 좌석 스탠딩",
            initCount = 10,
            price = 50000L,
            seatGrade = Ticket.SeatGrade.S_CLASS
        )
        val ticket = ticketController.create(concert.id!!, ticketCreateResource).body!!
        ticket shouldNotBe null

        val bookingCreateResource = BookingCreateResource(
            concertId = concert.id!!,
            ticketId = ticket.id!!
        )

        // when
        bookingController.create(bookingCreateResource).body!!
        bookingController.create(bookingCreateResource).body!!
        bookingController.create(bookingCreateResource).body!!
        val booking = bookingController.create(bookingCreateResource).body!!

        // then
        booking shouldNotBe null
        val count = bookingRedisRepository.get(booking.id!!.toString())
        count shouldBe 4
    }

    @Test
    @DisplayName("콘서트 티켓을 동시에 예매한다.")
    fun createBookingConcurrencyTest() {
//
//        // given
//        val concertCreateResource = ConcertCreateResource(
//            name = "아이유 콘서트 여름 2021",
//            performanceDate = LocalDateTime.now(),
//            viewingTime = 120,
//            grade = Concert.Grade.PG_12
//        )
//        val concert = concertController.create(concertCreateResource).body!!
//        concert shouldNotBe null
//
//        val ticketCreateResource = TicketCreateResource(
//            name = "아이유 콘서트 여름 2021 S 좌석 스탠딩",
//            initCount = 5,
//            price = 50000L,
//            seatGrade = Ticket.SeatGrade.S_CLASS
//        )
//        val ticket = ticketController.create(concert.id!!, ticketCreateResource).body!!
//        ticket shouldNotBe null
//
//        val bookingCreateResource = BookingCreateResource(
//            concertId = concert.id!!,
//            ticketId = ticket.id!!
//        )
//
//        // when
//        val booking: Booking = bookingController.create(bookingCreateResource).body!!
//        booking shouldNotBe null
//        booking.id!! shouldBe 1L
//
//        runBlocking {
//            val job = CoroutineScope(Dispatchers.IO).launch {
//                repeat(4) {
//                    val result = async { bookingController.create(bookingCreateResource).body!! }
//                    result.await()
//                }
//            }
//            job.join()
//        }
//
//        // then
//        val count = bookingRedisRepository.get(booking.id!!.toString())
//        count shouldBe 5
    }
}