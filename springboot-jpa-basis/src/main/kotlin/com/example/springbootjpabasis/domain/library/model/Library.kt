package com.example.springbootjpabasis.domain.library.model

import com.example.springbootjpabasis.domain.book.model.Book
import com.example.springbootjpabasis.domain.library.api.resources.LibraryCreateResources
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "library")
class Library(

    @Column(name = "name", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val name: String,

    @Column(name = "address", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    val address: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    val books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        val foundBook = this.books.find { it.id!! == book.id }
        foundBook?.let { existBook ->
            this.books.remove(existBook)
        }

        this.books.add(book)
    }

    companion object {
        fun from(libraryCreateResources: LibraryCreateResources): Library {
            libraryCreateResources.run {
                return Library(
                    name,
                    address
                )
            }
        }
    }
}