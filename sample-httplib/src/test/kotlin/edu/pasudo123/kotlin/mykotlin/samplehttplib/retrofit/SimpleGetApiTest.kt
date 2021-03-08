package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * https://kotlintesting.com/testing-retrofit-calls/
 */
@DisplayName("SimpleGetApi 는")
class SimpleGetApiTest {

    @Test
    fun getApiTest() {
        // given
        val simpleGetClient = SimpleGetClient()
        simpleGetClient.getTaylor()
    }
}