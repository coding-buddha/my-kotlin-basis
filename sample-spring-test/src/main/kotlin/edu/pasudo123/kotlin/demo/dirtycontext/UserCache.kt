package edu.pasudo123.kotlin.demo.dirtycontext

import org.springframework.stereotype.Component

/**
 * DirtyContext 를
 */
@Component
class UserCache {

    private val users = mutableSetOf<String>()

    fun addUser(name: String): Boolean {
        return users.add(name)
    }

    fun printUsers(): Unit {
        println(users)
    }

    fun getUsers(): Set<String> {
        return users
    }
}