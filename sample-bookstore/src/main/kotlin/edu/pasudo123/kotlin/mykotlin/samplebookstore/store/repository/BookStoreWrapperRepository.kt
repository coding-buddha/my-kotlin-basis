package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.repository

import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store.Store
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class BookStoreWrapperRepository(
    private val bookStoreRepository: BookStoreRepository
) {

    fun create(bookStore: Store): Store {
        return bookStoreRepository.save(bookStore)
    }

    fun findOneById(id: Long): Store {
        return bookStoreRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("book-store[$id] 가 없습니다.")
    }
}