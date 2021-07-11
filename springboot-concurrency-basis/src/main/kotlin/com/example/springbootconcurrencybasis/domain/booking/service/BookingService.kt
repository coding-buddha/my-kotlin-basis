package com.example.springbootconcurrencybasis.domain.booking.service

import com.example.springbootconcurrencybasis.domain.booking.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.booking.model.Booking
import com.example.springbootconcurrencybasis.domain.booking.repository.BookingCustomRepository
import com.example.springbootconcurrencybasis.domain.booking.repository.BookingRepository
import com.example.springbootconcurrencybasis.domain.conert.service.ConcertFindService
import com.example.springbootconcurrencybasis.global.exception.ErrorCode
import com.example.springbootconcurrencybasis.global.exception.detail.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookingService(
    private val bookingChecker: BookingChecker,
    private val bookingRepository: BookingRepository,
    private val bookingCustomRepository: BookingCustomRepository,
    private val concertFinderService: ConcertFindService,
) {

    fun create(bookingCreateResource: BookingCreateResource): Booking {
        val bookings = bookingCustomRepository.findAllBy(
            concertId = bookingCreateResource.concertId,
            ticketId = bookingCreateResource.ticketId
        )

        val concert = concertFinderService.findOneByIdOrThrow(bookingCreateResource.concertId)
        val ticket = concert.tickets.find { it.id == bookingCreateResource.ticketId }!!

        val booking = Booking().apply{
            this.set(concert)
            this.set(ticket)
        }

        bookingRepository.save(booking)
        bookingChecker.possibleBookingOrThrow(booking, bookings.count(), ticket)
        return booking
    }

    fun cancel(id: Long) {
        val booking = bookingRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException(ErrorCode.E100, "Booking[$id] 를 찾을 수 없습니다.")
        booking.delete()
        bookingRepository.save(booking)
        bookingChecker.decreaseBy(booking)
    }
}