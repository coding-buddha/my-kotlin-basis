package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.repository

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
}