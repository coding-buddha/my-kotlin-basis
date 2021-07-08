package com.example.springbootconcurrencybasis.domain.booking.api.resources

data class BookingCreateResource(
    val concertId: Long,
    val ticketId: Long
)