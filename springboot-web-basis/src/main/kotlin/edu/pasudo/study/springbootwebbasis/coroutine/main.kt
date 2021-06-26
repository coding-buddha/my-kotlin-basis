package edu.pasudo.study.springbootwebbasis.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("DeferredResultUnused")
fun main() {
    for (i in 50..60) {
        processAsync(i)
    }

    Thread.sleep(2000)
    println("========================")
    println("end")
    println("========================")
}

fun processAsync(i: Int) = GlobalScope.async {
    withContext(Dispatchers.IO) {
        val currentNumber = doSomething1(i)
        doSomething2(currentNumber)
    }
}

fun doSomething1(i: Int) : Int {
    val newVal1 = test1(i)
    val newVal2 = test2(newVal1)
    return test3(newVal2)
}

fun doSomething2(i: Int) {
    val newVal1 = test4(i)
    val newVal2 = test5(newVal1)
    test6(newVal2)
}

fun test1(i: Int): Int {
    println("test[1] : $i")
    return i
}

fun test2(i: Int): Int {
    println("test[2] : $i")
    return i
}

fun test3(i: Int): Int {
    println("test[3] : $i")
    return i
}

fun test4(i: Int): Int {
    println("test[4] : $i")
    return i
}

fun test5(i: Int): Int {
    println("test[5] : $i")
    return i
}

fun test6(i: Int): Int {
    println("test[6] : $i")
    return i
}