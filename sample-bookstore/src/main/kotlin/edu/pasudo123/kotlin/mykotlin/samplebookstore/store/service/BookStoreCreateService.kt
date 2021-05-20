package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.service

import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store.Store
import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.repository.BookStoreWrapperRepository
import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.resources.BookStoreCreateResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookStoreCreateService(
    private val bookStoreWrapperRepository: BookStoreWrapperRepository
) {
    fun create(bookStoreCreateResource: BookStoreCreateResource): Store {
        return bookStoreWrapperRepository.create(bookStoreCreateResource.toEntity())
    }
}