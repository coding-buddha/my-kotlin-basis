package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain

import edu.pasudo123.kotlin.mykotlin.samplebookstore.exception.BookRentalException
import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store.Store
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "book", indexes = [
    Index(name = "isbn_idx", columnList = "isbn")
])
class Book (
    nameParam: String,
    authorParam: String,
    publisherParam: String,
    isbnParam: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val name: String = nameParam

    @Column(name = "author", columnDefinition = "VARCHAR(60)", length = 60, nullable = false)
    val author: String = authorParam

    @Column(name = "publisher", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    val publisher: String = publisherParam

    @Column(name = "isbn", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val isbn: String = isbnParam

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_status", columnDefinition = "ENUM('AVAILABLE', 'NOT_POSSIBLE', 'ORDER_SOON', 'DO_NOT_HOLD')", nullable = false)
    var rentalStatus: RentalStatus = RentalStatus.DO_NOT_HOLD
        private set

    @Column(name = "count", columnDefinition = "BIGINT", nullable = false)
    var count = 0

    @Column(name = "created_at", columnDefinition = "DATETIME", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    /**
     * nullable 을 붙인건 스키마 컬럼에 대해서 null 이 될 수 없음을 명시
     * optional 을 붙인건 프록시 기능에 null 을 허용하지 않고 지연로딩을 하기위한 장치
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Store::class, optional = false)
    @JoinColumn(name = "book_store_id", referencedColumnName = "id", nullable = false)
    var bookStore: Store? = null

    enum class RentalStatus {
        AVAILABLE,      // 대여가능
        ON_RENTAL,      // 대여중
        NOT_POSSIBLE,   // 대여불가
        ORDER_SOON,     // 입고예정
        DO_NOT_HOLD     // 보유하지 않음
    }

    /**
     * 아래의 상태에서만 변경이 가능하다.
     *
     * - 대여가능 -> 대여중
     */
    fun changeOnRental() {
        if(this.rentalStatus != RentalStatus.AVAILABLE)
            throw BookRentalException("현재 확인된 책은 [대여중] 상태로 변경할 수 없습니다. : [${this.id}] ${this.rentalStatus}")

        this.rentalStatus = RentalStatus.ON_RENTAL
    }

    /**
     * 아래의 상태에서만 변경이 가능하다.
     *
     * - 대여중 -> 대여가능
     * - 입고예정 -> 대여가능
     * - 보유하지않음 -> 대여가능
     */
    fun changeAvailableRental() {
        if(this.rentalStatus != RentalStatus.ON_RENTAL
            && this.rentalStatus != RentalStatus.ORDER_SOON
            && this.rentalStatus != RentalStatus.DO_NOT_HOLD)
                throw BookRentalException("현재 확인된 책은 [대여가능] 상태로 변경할 수 없습니다. : [${this.id}] ${this.rentalStatus}")

        this.rentalStatus = RentalStatus.AVAILABLE
    }

    // TODO race condition 은 어떻게 할 것인가.
    fun bookCountPlus() {
        this.count++
    }

    // TODO race condition 은 어떻게 할 것인가.
    fun bookCountMinus() {
        this.count--;
    }
}