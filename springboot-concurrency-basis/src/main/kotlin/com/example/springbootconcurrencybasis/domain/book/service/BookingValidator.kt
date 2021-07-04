package com.example.springbootconcurrencybasis.domain.book.service

import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRedisRepository
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import org.springframework.stereotype.Service

@Service
class BookingValidator(
    private val bookingRedisRepository: BookingRedisRepository
) {

    fun possibleBookingOrThrow(bookings: List<Booking>, ticket: Ticket) {

        val booking = bookings.first()
        var currentCount = bookingRedisRepository.get(booking.id!!.toString())

        if(currentCount == null) {
            bookingRedisRepository.save(booking.id!!.toString(), bookings.count())
            currentCount = bookings.count()
        }

        if(ticket.initCount < currentCount) {
            throw Exception("티켓 예약이 끝났습니다.")
        }

        bookingRedisRepository.save(booking.id!!.toString(), currentCount + 1)
    }

    fun decreaseBookingCount(booking: Booking) {
        val currentCount = bookingRedisRepository.get(booking.id!!.toString()) ?: return
        bookingRedisRepository.save(booking.id!!.toString(), currentCount - 1)
    }
}