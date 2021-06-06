package edu.pasudo.study.springbootredisbasis.geo.dummy

object FixtureIdGenerator {

    var currentId: Long = 0

    fun id(): Long {
        return currentId++
    }
}