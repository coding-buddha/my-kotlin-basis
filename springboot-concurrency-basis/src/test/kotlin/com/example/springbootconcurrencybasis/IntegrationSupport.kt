package com.example.springbootconcurrencybasis

import com.example.springbootconcurrencybasis.config.TestRedisConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@ActiveProfiles("test")
@SpringBootTest(
    classes = [SpringbootConcurrenyBasisApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@TestPropertySource(locations = ["classpath:application-test.yml"])
@Import(value = [TestRedisConfiguration::class])
class IntegrationSupport {
}