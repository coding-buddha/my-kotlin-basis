package edu.pasudo123.study.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * https://spring.io/guides/gs/rest-hateoas/
 * https://www.baeldung.com/spring-hateoas-tutorial
 */
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
