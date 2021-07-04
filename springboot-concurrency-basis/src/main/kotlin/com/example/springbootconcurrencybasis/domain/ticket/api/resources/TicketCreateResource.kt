package com.example.springbootconcurrencybasis.domain.ticket.api.resources

import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket

data class TicketCreateResource(
    val name: String,
    val initCount: Long,
    val price: Long,
    val seatGrade: Ticket.SeatGrade
)