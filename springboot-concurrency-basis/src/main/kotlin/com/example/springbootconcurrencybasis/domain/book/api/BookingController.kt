package com.example.springbootconcurrencybasis.domain.book.api

import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.book.model.Booking
import com.example.springbootconcurrencybasis.domain.book.service.BookingService
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bookings")
class BookingController(
    private val bookingService: BookingService
) {

    companion object : KLogging()

    @PostMapping
    fun create(
        @RequestBody bookingCreateResource: BookingCreateResource
    ): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.create(bookingCreateResource))
    }

    @DeleteMapping("{id}")
    fun cancel(
        @PathVariable id: Long
    ): ResponseEntity<Unit> {
        bookingService.cancel(id)
        return ResponseEntity.ok(Unit)
    }
}