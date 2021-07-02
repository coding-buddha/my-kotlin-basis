package com.example.springbootconcurrencybasis.domain.ticket.api.resources

import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket

class TicketCreateResource(
    val name: String,
    val seatInfo: String,
    val desc: String,
    val price: Long,
    val seatGrade: Ticket.SeatGrade
)