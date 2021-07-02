package com.example.springbootconcurrencybasis.domain.book.api.resources

data class BookingCreateResource(
    val concertId: Long,
    val ticketId: Long
)