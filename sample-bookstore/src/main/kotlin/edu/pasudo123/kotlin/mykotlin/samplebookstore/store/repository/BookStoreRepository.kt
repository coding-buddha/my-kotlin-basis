package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.repository

import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookStoreRepository : JpaRepository<Store, Long> {
}