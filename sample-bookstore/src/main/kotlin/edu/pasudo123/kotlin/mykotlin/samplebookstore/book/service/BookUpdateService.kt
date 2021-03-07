package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.repository.BookWrapperRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookUpdateService(
    private val bookFetchService: BookFetchService,
    private val bookWrapperRepository: BookWrapperRepository
) {

    fun updateRentalAvailableStatus(id: Long) {
        val book = bookFetchService.findOneById(id)
        book.changeAvailableRental()
        bookWrapperRepository.update(book)
    }

    fun updateRentalOnStatus(id: Long) {
        val book = bookFetchService.findOneById(id)
        book.changeOnRental()
        bookWrapperRepository.update(book)
    }
}