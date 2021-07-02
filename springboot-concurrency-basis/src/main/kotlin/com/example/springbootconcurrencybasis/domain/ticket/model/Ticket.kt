package com.example.springbootconcurrencybasis.domain.ticket.model

import com.example.springbootconcurrencybasis.domain.BaseEntity
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.ticket.api.resources.TicketCreateResource
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "ticket")
class Ticket private constructor(
    @Column(name = "name", columnDefinition = "VARCHAR(60) comment '티켓명'", nullable = false)
    val name: String,

    @Column(name = "seat_info", columnDefinition = "VARCHAR(60) comment '좌석정보'", nullable = false)
    val seatInfo: String,

    @Column(name = "description", columnDefinition = "VARCHAR(100) comment '좌석설명'", nullable = false)
    val desc: String,

    @Column(name = "price", columnDefinition = "BIGINT", nullable = false)
    val price: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_grade", columnDefinition = "VARCHAR(30) comment '좌석등급'", nullable = false)
    val seatGrade: SeatGrade
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Concert::class)
    @JoinColumn(name = "concert_id")
    private var concert: Concert? = null

    enum class SeatGrade(name: String, desc: String) {
        S_CLASS("S 클래스", "공연자가 엄청 가까이 보임"),
        A_CLASS("A 클래스", "공연자가 가까이 보임"),
        B_CLASS("B 클래스", "공연자가 육안 멀리 보임"),
        C_CLASS("C 클래스", "공연자가 잘 안보임"),
    }

    companion object {
        fun from(ticketCreateResource: TicketCreateResource): Ticket {
            return ticketCreateResource.run {
                Ticket(
                    name = this.name,
                    desc = this.desc,
                    seatInfo = this.seatInfo,
                    price = this.price,
                    seatGrade = this.seatGrade
                )
            }
        }
    }

    fun set(concert: Concert) {
        this.concert = concert
    }
}