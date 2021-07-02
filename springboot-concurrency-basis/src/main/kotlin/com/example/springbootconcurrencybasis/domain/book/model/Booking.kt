package com.example.springbootconcurrencybasis.domain.book.model

import com.example.springbootconcurrencybasis.domain.BaseEntity
import com.example.springbootconcurrencybasis.domain.book.api.resources.BookingCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "booking")
class Booking: BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Concert::class)
    @JoinColumn(name = "concert_id", nullable = false)
    private var concert: Concert? = null

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Ticket::class)
    @JoinColumn(name = "ticket_id", nullable = false)
    private var ticket: Ticket? = null

    @Column(name = "deleted", columnDefinition = "BIT comment '삭제여부'", nullable = false)
    private var deleted: Boolean = false

    fun set(concert: Concert) {
        this.concert = concert
    }

    fun set(ticket: Ticket) {
        this.ticket = ticket
    }

    fun delete() {
        this.deleted = true
    }

    companion object {
        fun from(bookingCreateResource: BookingCreateResource): Booking {
            return bookingCreateResource.run {
                Booking()
            }
        }
    }
}