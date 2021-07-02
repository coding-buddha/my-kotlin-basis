package com.example.springbootconcurrencybasis.domain.book.api

import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.repository.BookingRepository
import com.example.springbootconcurrencybasis.domain.conert.service.ConcertFindService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("bookings")
class BookingController(
    private val bookingRepository: BookingRepository,
    private val concertFinderService: ConcertFindService
) {

    @PostMapping
    fun create(
        bookingCreateResource: BookingCreateResource
    ): ResponseEntity<Booking> {
        val concert = concertFinderService.findOneById(bookingCreateResource.concertId)
        val ticket = concert.tickets.find { it.id == bookingCreateResource.ticketId }!!
        val booking = Booking().apply{
            this.set(concert)
            this.set(ticket)
        }
        bookingRepository.save(booking)
        return ResponseEntity.ok(booking)
    }
}