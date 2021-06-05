package edu.pasudo.study.springbootredisbasis

import edu.pasudo.study.springbootredisbasis.config.TestRedisConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.test.context.TestPropertySource

@Profile("test")
@SpringBootTest(
    classes = [SpringbootRedisBasisApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@TestPropertySource(locations = ["classpath:application-test.yml"])
@Import(value = [TestRedisConfiguration::class])
class IntegrationTest {
}