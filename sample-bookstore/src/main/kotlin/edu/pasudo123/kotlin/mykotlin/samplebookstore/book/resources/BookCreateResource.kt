package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.resources

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book

data class BookCreateResource(
    private val name: String,
    private val author: String,
    private val publisher: String,
    private val isbn: String
) {

    fun createBookEntity(): Book {
        return Book(name, author, publisher, isbn)
    }
}
