package com.example.springbootjpabasis.book.repository

import com.example.springbootjpabasis.book.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>