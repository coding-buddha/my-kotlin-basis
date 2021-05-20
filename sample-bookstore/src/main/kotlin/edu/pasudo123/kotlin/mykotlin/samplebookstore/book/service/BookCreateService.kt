package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.repository.BookWrapperRepository
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.resources.BookCreateResource
import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.repository.BookStoreWrapperRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookCreateService(
    private val bookWrapperRepository: BookWrapperRepository,
    private val bookStoreWrapperRepository: BookStoreWrapperRepository
) {

    fun create(bookCreateResource: BookCreateResource, storeId: Long): Book {
        val store = bookStoreWrapperRepository.findOneById(storeId)
        val book = bookCreateResource.toEntity()
        book.setStore(store)
        return bookWrapperRepository.create(book)
    }
}