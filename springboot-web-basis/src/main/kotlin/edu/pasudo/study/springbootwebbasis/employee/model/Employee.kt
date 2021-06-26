package edu.pasudo.study.springbootwebbasis.employee.model

import kotlin.random.Random

data class Employee(
    val id: Long,
    val name: String,
    val age: Int
) {

    companion object {
        fun create(): Employee {
            return Employee(
                id = Random.nextLong(1, 99999),
                name = "PARK",
                age = Random.nextInt(20, 80)
            )
        }
    }
}