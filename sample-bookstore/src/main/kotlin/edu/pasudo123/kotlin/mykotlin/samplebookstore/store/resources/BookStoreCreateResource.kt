package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.resources

import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store.Store

class BookStoreCreateResource(
    private val name: String,
    private val address: String,
) {

    fun toEntity(): Store {
        return Store(name, address)
    }
}