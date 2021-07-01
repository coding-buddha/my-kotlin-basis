package com.example.springbootconcurrencybasis.domain.ticket.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table
class Ticket(
    @Column(name = "name", columnDefinition = "VARCHAR(60)", nullable = false)
    private val name: String
) {
}