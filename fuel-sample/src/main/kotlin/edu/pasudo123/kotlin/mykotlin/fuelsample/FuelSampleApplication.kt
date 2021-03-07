package edu.pasudo123.kotlin.mykotlin.fuelsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuelSampleApplication

fun main(args: Array<String>) {
    runApplication<FuelSampleApplication>(*args)
}
