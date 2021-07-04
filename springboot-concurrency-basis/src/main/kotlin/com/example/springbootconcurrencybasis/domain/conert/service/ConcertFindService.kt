package com.example.springbootconcurrencybasis.domain.conert.service

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertCustomRepository
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional(readOnly = true)
class ConcertFindService(
    private val concertRepository: ConcertRepository,
    private val concertCustomRepository: ConcertCustomRepository
) {

    fun findOneByIdOrThrow(id: Long) : Concert {
        return concertRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Concert[$id] 를 찾을 수 없습니다.")
    }
}