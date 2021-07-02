package com.example.springbootconcurrencybasis.domain.conert.service

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.example.springbootconcurrencybasis.domain.conert.repository.ConcertRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional(readOnly = true)
class ConcertFindService(
    private val concertRepository: ConcertRepository
) {

    fun findOneById(id: Long) : Concert {
        return concertRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Concert[$id] 를 찾을 수 없습니다.")
    }
}