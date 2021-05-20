package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.api

import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store.Store
import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.resources.BookStoreCreateResource
import edu.pasudo123.kotlin.mykotlin.samplebookstore.store.service.BookStoreCreateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bookstores")
class BookStoreController(
    private val bookStoreCreateService: BookStoreCreateService
) {

    @PostMapping
    fun createBookStore(
        @RequestBody bookStoreCreateResource: BookStoreCreateResource
    ): ResponseEntity<Store> {
        return ResponseEntity.ok().body(bookStoreCreateService.create(bookStoreCreateResource))
    }
}