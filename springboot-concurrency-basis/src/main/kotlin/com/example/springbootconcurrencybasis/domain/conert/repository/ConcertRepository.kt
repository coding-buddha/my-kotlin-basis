package com.example.springbootconcurrencybasis.domain.conert.repository

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConcertRepository: JpaRepository<Concert, Long>