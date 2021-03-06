package edu.pasudo123.kotlin.mykotlin.samplebookstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleBookstoreApplication

fun main(args: Array<String>) {
    runApplication<SampleBookstoreApplication>(*args)
}
