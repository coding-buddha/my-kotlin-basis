package com.example.springbootconcurrencybasis.domain.conert.api

import com.example.springbootconcurrencybasis.domain.conert.api.resource.ConcertCreateResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertRepository
import com.example.springbootconcurrencybasis.domain.conert.service.ConcertFindService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("concerts")
class ConcertController(
    private val concertRepository: ConcertRepository,
    private val concertFindService: ConcertFindService
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
        val concert = concertFindService.findOneByIdOrThrow(id)
        return ResponseEntity.ok(concert)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Concert>> {
        val concerts = concertRepository.findAll()
        return ResponseEntity.ok(concerts)
    }
}