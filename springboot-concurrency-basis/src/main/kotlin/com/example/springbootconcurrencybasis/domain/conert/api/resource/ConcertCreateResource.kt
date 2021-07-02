package com.example.springbootconcurrencybasis.domain.conert.api.resource

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import java.time.LocalDateTime

data class ConcertCreateResource(
    val name: String,
    val performanceDate: LocalDateTime,
    val viewingTime: Long,
    val grade: Concert.Grade
)
