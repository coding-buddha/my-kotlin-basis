package com.example.springbootconcurrencybasis.domain.book.service

import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.repository.BookingCustomRepository
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRedisRepository
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRepository
import com.example.springbootconcurrencybasis.domain.conert.service.ConcertFindService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookingService(
    private val bookingRedisRepository: BookingRedisRepository,
    private val bookingRepository: BookingRepository,
    private val bookingCustomRepository: BookingCustomRepository,
    private val concertFinderService: ConcertFindService,
) {

    fun create(bookingCreateResource: BookingCreateResource): Booking {
        val bookings = bookingCustomRepository.findAllBy(
            concertId = bookingCreateResource.concertId,
            ticketId = bookingCreateResource.ticketId
        )

        val currentCount = bookingRedisRepository.get(bookings.first().id!!.toString())


        val concert = concertFinderService.findOneById(bookingCreateResource.concertId)
        val ticket = concert.tickets.find { it.id == bookingCreateResource.ticketId }!!
        val booking = Booking().apply{
            this.set(concert)
            this.set(ticket)
        }
        bookingRepository.save(booking)
        return booking
    }
}