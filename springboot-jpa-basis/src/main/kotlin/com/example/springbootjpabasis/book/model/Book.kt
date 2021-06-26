package com.example.springbootjpabasis.book.model

import com.example.springbootjpabasis.book.api.resources.BookCreateResources
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "book", indexes = [
    Index(name = "isbn_idx", columnList = "isbn")
])
class Book(

    @Column(name = "name", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val name: String,

    @Column(name = "author", columnDefinition = "VARCHAR(60)", length = 60, nullable = false)
    val author: String,

    @Column(name = "publisher", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    val publisher: String,

    @Column(name = "isbn", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val isbn: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    companion object {
        fun from(bookCreateResources: BookCreateResources): Book {
            bookCreateResources.run {
                return Book(
                    name,
                    author,
                    publisher,
                    UUID.randomUUID().toString()
                )
            }
        }
    }
}