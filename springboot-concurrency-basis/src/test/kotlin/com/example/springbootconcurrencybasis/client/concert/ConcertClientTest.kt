package com.example.springbootconcurrencybasis.client.concert

import com.example.springbootconcurrencybasis.IntegrationSupport
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment

@DisplayName("ConcertClient 는")
class ConcertClientTest : IntegrationSupport() {

    @Autowired
    private lateinit var env: Environment
    private var maker: ConcertClientMaker? = null
    private var client: ConcertClientMaker.ConcertClient? = null

    @BeforeEach
    fun init() {
        val host = env.getProperty("client.concert.host").toString()
        val useDummy = env.getProperty("client.concert.use-dummy").toBoolean()
        this.maker = ConcertClientMaker(host, useDummy)
        this.client = maker!!.createClient()
    }

    @Test
    @DisplayName("콘서트를 생성한다.")
    fun createConcertTest() {

    }
}