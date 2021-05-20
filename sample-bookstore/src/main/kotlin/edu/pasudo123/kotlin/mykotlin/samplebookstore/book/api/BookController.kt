package edu.pasudo123.kotlin.mykotlin.samplebookstore.book.api

import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.domain.Book
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.resources.BookCreateResource
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service.BookCreateService
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service.BookFetchService
import edu.pasudo123.kotlin.mykotlin.samplebookstore.book.service.BookUpdateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    private val bookCreateService: BookCreateService,
    private val bookFetchService: BookFetchService,
    private val bookUpdateService: BookUpdateService
) {

    @PostMapping("bookstore/{id}")
    fun createBook(
        @RequestBody bookCreateResource: BookCreateResource,
        @PathVariable("id") storeId: Long
    ): ResponseEntity<Book> {
        return ResponseEntity.ok().body(bookCreateService.create(bookCreateResource, storeId))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Book>> {
        return ResponseEntity.ok().body(bookFetchService.findAll())
    }

    @GetMapping("{id}")
    fun findOneById(@PathVariable id: Long): ResponseEntity<Book> {
        return ResponseEntity.ok().body(bookFetchService.findOneById(id))
    }

    @PatchMapping("{id}/rental-available")
    fun updateBookRentalAvailable(
        @PathVariable id: Long,
    ): Unit {
        bookUpdateService.updateRentalAvailableStatus(id)
    }

    @PatchMapping("{id}/rental-on")
    fun updateBookRentalOn(
        @PathVariable id: Long
    ): Unit {
        bookUpdateService.updateRentalOnStatus(id)
    }
}