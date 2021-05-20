package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.resources

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book

data class BookCreateResource(
    private val name: String,
    private val author: String,
    private val publisher: String,
    private val isbn: String
) {

    fun toEntity(): Book {
        return Book(name, author, publisher, isbn)
    }
}