package com.example.springbootconcurrencybasis.domain.book.repository

import com.example.springbootconcurrencybasis.domain.book.model.Booking
import org.springframework.data.jpa.repository.JpaRepository

interface BookingRepository : JpaRepository<Booking, Long>