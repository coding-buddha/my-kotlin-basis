package com.example.springbootjpabasis.domain.book.api

import com.example.springbootjpabasis.domain.book.api.resources.BookCreateResources
import com.example.springbootjpabasis.domain.book.model.Book
import com.example.springbootjpabasis.domain.book.repository.BookRepository
import com.example.springbootjpabasis.domain.library.repository.LibraryRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException

@Api("책 도메인 API")
@RestController
@RequestMapping("books")
@Transactional
class BookController(
    private val bookRepository: BookRepository,
    private val libraryRepository: LibraryRepository
) {

    @ApiOperation(value = "책을 추가한다.")
    @PostMapping
    fun create(@RequestBody bookCreateResources: BookCreateResources) : ResponseEntity<Book> {
        val libraryOpt = libraryRepository.findById(bookCreateResources.libraryId)
        if (libraryOpt.isEmpty) {
            throw EntityNotFoundException("There is no library[${bookCreateResources.libraryId}] for adding book")
        }

        val book = bookRepository.save(Book.from(bookCreateResources))
        book.setLibrary(libraryOpt.get())
        return ResponseEntity.ok(book)
    }

    @ApiOperation(value = "책을 전체 조회한다.")
    @GetMapping
    fun fetchAll() : ResponseEntity<List<Book>> {
        val books = bookRepository.findAll()
        return ResponseEntity.ok(books)
    }

    @ApiOperation(value = "책을 단일 조회한다.")
    @GetMapping("{id}")
    fun fetchOneById(@PathVariable("id") id: Long) : ResponseEntity<Book> {
        val bookOpt = bookRepository.findById(id)
        if (bookOpt.isEmpty) {
            throw EntityNotFoundException("Does not exist book[$id]")
        }

        return ResponseEntity.ok(bookOpt.get())
    }
}