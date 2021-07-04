package com.example.springbootconcurrencybasis.domain.book.service

import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.repository.BookingCustomRepository
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRepository
import com.example.springbootconcurrencybasis.domain.conert.service.ConcertFindService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional
class BookingService(
    private val bookingValidator: BookingValidator,
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
        bookingValidator.possibleBookingOrThrow(bookings, ticket)

        val booking = Booking().apply{
            this.set(concert)
            this.set(ticket)
        }
        bookingRepository.save(booking)
        return booking
    }

    fun cancel(id: Long) {
        val bookings = bookingCustomRepository.findOneById(id)
        if (bookings.isEmpty()) {
            throw EntityNotFoundException("Booking[$id] 를 찾을 수 없습니다.")
        }

        val myBooking = bookings.first()
        myBooking.delete()
        bookingRepository.save(myBooking)
        bookingValidator.decreaseBookingCount(myBooking)
    }
}