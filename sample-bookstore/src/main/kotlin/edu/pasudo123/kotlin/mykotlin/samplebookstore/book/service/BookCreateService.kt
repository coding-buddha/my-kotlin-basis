package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.repository.BookWrapperRepository
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.resources.BookCreateResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookCreateService(
    private val bookWrapperRepository: BookWrapperRepository
) {

    fun create(bookCreateResource: BookCreateResource): Book {
        return bookWrapperRepository.create(bookCreateResource.createBookEntity())
    }
}