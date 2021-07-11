package com.example.springbootconcurrencybasis.domain.booking.repository

import com.example.springbootconcurrencybasis.Helper
import com.example.springbootconcurrencybasis.IntegrationSupport
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DisplayName("BookingRedisRepository 는")
internal class BookingRedisRepositoryTest : IntegrationSupport() {

    @Autowired
    private lateinit var helper: Helper

    @Autowired
    private lateinit var bookingRedisRepository: BookingRedisRepository

    @Test
    @DisplayName("레디스 키를 감시한다.")
    fun watchBookingTest() {

        // given
        val concert = helper.createConcert()
        val ticket = helper.createTicket(concertId = concert.id!!)
        val booking = helper.createBooking(concertId = concert.id!!, ticketId = ticket.id!!)

        // when
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).launch {
                async { bookingRedisRepository.watchBookingForTest(booking) }
                async { bookingRedisRepository.watchBookingForTest(booking) }
                async { bookingRedisRepository.watchBookingForTest(booking) }
                async { bookingRedisRepository.watchBookingForTest(booking) }
            }
            job.join()
        }

        // then
        val result = bookingRedisRepository.get(booking)
        result shouldBe 1
    }
}