package edu.pasudo.study.springbootredisbasis.geo.model

data class Location(
    val id: Long,
    val name: String? = null,
    val longitude: Double,
    val latitude: Double
)
