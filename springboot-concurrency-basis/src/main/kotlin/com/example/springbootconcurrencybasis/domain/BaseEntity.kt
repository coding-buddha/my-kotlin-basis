package com.example.springbootconcurrencybasis.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : Serializable {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private val createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false, updatable = true)
    private val modifiedAt: LocalDateTime? = null
}