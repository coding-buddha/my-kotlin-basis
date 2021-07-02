package com.example.springbootconcurrencybasis.domain.conert.api

import com.example.springbootconcurrencybasis.domain.conert.api.resource.ConcertCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("concerts")
class ConcertController(
    private val concertRepository: ConcertRepository
) {

    @PostMapping
    fun create(
        @RequestBody concertCreateResource: ConcertCreateResource
    ): ResponseEntity<Concert> {
        val concert = concertRepository.save(Concert.from(concertCreateResource))
        return ResponseEntity.ok(concert)
    }

    @GetMapping("{id}")
    fun findOneById(
        @PathVariable id: Long
    ): ResponseEntity<Concert> {
        val concert = concertRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Concert[$id] 를 찾을 수 없습니다.")
        return ResponseEntity.ok(concert)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Concert>> {
        val concerts = concertRepository.findAll()
        return ResponseEntity.ok(concerts)
    }
}