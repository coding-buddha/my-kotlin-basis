package com.example.springbootconcurrencybasis.domain.booking.service

import com.example.springbootconcurrencybasis.domain.booking.model.Booking
import com.example.springbootconcurrencybasis.domain.booking.repository.BookingRedisRepository
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class BookingChecker(
    private val bookingRedisRepository: BookingRedisRepository
) {

    companion object : KLogging()

    /**
     * bookingCount 는 lock 이 안걸려서, 동일한 값이 나오더라도
     * incrValue 는 레디스 싱글스레드로 동작하기에 동시성 체크가 가능하다.
     */
    fun possibleBookingOrThrow(booking: Booking, bookingCount: Int, ticket: Ticket) {
        val incrValue = bookingRedisRepository.increaseBooking(booking, bookingCount)
        if (incrValue > ticket.initCount) {
            this.decreaseBy(booking)
            throw Exception("티켓 예약이 끝났습니다.")
        }
    }

    fun decreaseBy(booking: Booking) {
        bookingRedisRepository.decreaseBooking(booking)
    }
}