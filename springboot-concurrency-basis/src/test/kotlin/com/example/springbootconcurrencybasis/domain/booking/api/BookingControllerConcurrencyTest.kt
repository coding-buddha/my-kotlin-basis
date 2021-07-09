package com.example.springbootconcurrencybasis.domain.booking.api

import com.example.springbootconcurrencybasis.IntegrationSupport
import com.example.springbootconcurrencybasis.client.concert.ConcertClient
import com.example.springbootconcurrencybasis.client.concert.ConcertClientMaker
import com.example.springbootconcurrencybasis.client.concert.resource.ConcertResource
import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment

@DisplayName("BookingControllerConcurrency 는")
class BookingControllerConcurrencyTest : IntegrationSupport() {

    @Autowired
    private lateinit var env: Environment
    @Autowired
    private lateinit var mapper: ObjectMapper

    private var maker: ConcertClientMaker? = null
    private var client: ConcertClient? = null

    @BeforeEach
    fun init() {
        val host = env.getProperty("client.concert.host").toString()
        val useDummy = env.getProperty("client.concert.use-dummy").toBoolean()
        this.maker = ConcertClientMaker(host, useDummy, mapper)
        this.client = maker!!.createClient()
    }

    @Test
    @DisplayName("콘서트를 생성한다.")
    fun createConcertTest() {

        // given
        val resource = ConcertResource.CreateRequest(
            name = "아이유 콘서트 여름 2021",
            performanceDate = "2021-08-01 18:00:00",
            viewingTime = 120,
            grade = Concert.Grade.PG_12.name
        )

        // when
        val concert = client!!.createConcert(resource)
            .execute()
            .body()

        // then
        concert shouldNotBe null
        concert!!.name shouldBe "아이유 콘서트 여름 2021"
        concert.viewingTime shouldBe 120
        concert.grade shouldBe Concert.Grade.PG_12.name
    }
}