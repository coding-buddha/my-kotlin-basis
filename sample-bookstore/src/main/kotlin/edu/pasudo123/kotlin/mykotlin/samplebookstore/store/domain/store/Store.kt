package edu.pasudo123.kotlin.mykotlin.samplebookstore.store.domain.store

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "store")
class Store(

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", columnDefinition = "VARCHAR(80)", length = 80, nullable = false)
    val name: String = ""

    @Column(name = "address", columnDefinition = "VARCHAR(255)", length = 200, nullable = false)
    val address: String = ""
}