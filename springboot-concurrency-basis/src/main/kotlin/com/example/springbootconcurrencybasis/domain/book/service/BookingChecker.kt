package com.example.springbootconcurrencybasis.domain.book.service

import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRedisRepository
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class BookingChecker(
    private val bookingRedisRepository: BookingRedisRepository
) {

    companion object : KLogging()

    fun possibleBookingOrThrow(booking: Booking, bookingCount: Int, ticket: Ticket) {
        val increaseResultCount = bookingRedisRepository.increaseBooking(booking.id!!.toString(), bookingCount)

        logger.info { "==> increaseCount : $increaseResultCount, bookingCount : $bookingCount" }
        if (increaseResultCount > ticket.initCount) {
            this.decreaseBy(booking)
            throw Exception("티켓 예약이 끝났습니다.")
        }
    }

    fun decreaseBy(booking: Booking) {
        bookingRedisRepository.decreaseBooking(booking.id!!.toString())
    }
}