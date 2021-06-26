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
import javax.persistence.EntityNotFoundException

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
    fun fetchAll() : ResponseEntity<List<Book>> {
        val books = bookRepository.findAll()
        return ResponseEntity.ok(books)
    }

    @GetMapping("{id}")
    fun fetchOneById(@PathVariable("id") id: Long) : ResponseEntity<Book> {
        val bookOpt = bookRepository.findById(id)
        if (bookOpt.isEmpty) {
            throw EntityNotFoundException("Does not exist book[$id]")
        }

        return ResponseEntity.ok(bookOpt.get())
    }
}