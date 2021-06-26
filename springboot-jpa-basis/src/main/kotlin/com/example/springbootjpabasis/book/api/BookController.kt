package com.example.springbootjpabasis.book.api

import com.example.springbootjpabasis.book.api.resources.BookCreateResources
import com.example.springbootjpabasis.book.model.Book
import com.example.springbootjpabasis.book.repository.BookRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookRepository: BookRepository
) {

    @PostMapping
    fun create(@RequestBody bookCreateResources: BookCreateResources) : ResponseEntity<Book> {
        val book = bookRepository.save(Book.from(bookCreateResources))
        return ResponseEntity.ok(book)
    }

    @GetMapping
    fun fetchAll() : ResponseEntity<Book> {
        TODO("구현이 필요하다.")
    }

    @GetMapping("{id}")
    fun fetchOneById(@PathVariable("id") id: Long) : ResponseEntity<List<Book>> {
        TODO("구현이 필요하다.")
    }
}