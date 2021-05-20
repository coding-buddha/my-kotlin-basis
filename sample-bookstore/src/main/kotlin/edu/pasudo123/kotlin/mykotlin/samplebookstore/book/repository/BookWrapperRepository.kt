package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.repository

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class BookWrapperRepository(
    private val bookRepository: BookRepository
) {

    fun create(book: Book): Book {
        return bookRepository.save(book)
    }

    fun findAll(): List<Book> {
        return bookRepository.findAll()
    }

    fun findOneById(id: Long): Book {
        return bookRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("book[$id] 가 없습니다.")
    }

    fun update(book: Book): Book {
        return bookRepository.save(book)
    }
}