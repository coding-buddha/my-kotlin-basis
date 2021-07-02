package com.example.springbootconcurrencybasis.domain.book.api

import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.service.BookingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bookings")
class BookingController(
    private val bookingService: BookingService
) {

    @PostMapping
    fun create(
        @RequestBody bookingCreateResource: BookingCreateResource
    ): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.create(bookingCreateResource))
    }
}