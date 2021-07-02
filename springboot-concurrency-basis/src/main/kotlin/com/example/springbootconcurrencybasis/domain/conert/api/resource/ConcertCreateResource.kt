package com.example.springbootconcurrencybasis.domain.conert.api.resource

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ConcertCreateResource(
    val name: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val performanceDate: LocalDateTime,
    val viewingTime: Long,
    val grade: Concert.Grade
)