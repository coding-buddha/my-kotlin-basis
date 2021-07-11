package com.example.springbootconcurrencybasis.domain.booking.repository

import com.example.springbootconcurrencybasis.util.Helper
import com.example.springbootconcurrencybasis.IntegrationSupport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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

        CoroutineScope(Dispatchers.IO).launch {
            async { bookingRedisRepository.watchBooking(booking) }
            async { bookingRedisRepository.watchBooking(booking) }
        }
    }
}