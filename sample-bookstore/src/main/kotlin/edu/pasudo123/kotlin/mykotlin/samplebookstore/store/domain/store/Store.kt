package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "book_store")
class Store(
    nameParam: String,
    addressParam: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val name: String = nameParam

    @Column(name = "address", columnDefinition = "VARCHAR(255)", length = 200, nullable = false)
    val address: String = addressParam

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookStore")
    val books: List<Book> = mutableListOf()


}