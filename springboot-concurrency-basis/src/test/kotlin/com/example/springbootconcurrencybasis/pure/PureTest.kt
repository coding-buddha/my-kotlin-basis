package com.example.springbootconcurrencybasis.pure

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PureTest {

    @Test
    @DisplayName("isBlank 확인용")
    fun test01() {
        var line: String = ""

        println(line.isBlank())
        println(line.isEmpty())
    }
}