package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.repository.BookWrapperRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookFetchService(
    private val bookWrapperRepository: BookWrapperRepository
) {
    fun findAll(): List<Book> {
        return bookWrapperRepository.findAll()
    }

    fun findOneById(id: Long): Book {
        return bookWrapperRepository.findOneById(id)
    }
}