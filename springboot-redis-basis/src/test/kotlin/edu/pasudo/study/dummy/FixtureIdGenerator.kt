package edu.pasudo.study.dummy

object FixtureIdGenerator {

    var currentId: Long = 0

    fun id(): Long {
        return currentId++
    }
}