package edu.pasudo.study.springbootredisbasis.config

import mu.KLogging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.test.context.TestConfiguration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * https://github.com/ozimov/embedded-redis
 */
@TestConfiguration
class TestRedisConfiguration(
    redisProperties: RedisProperties
) {

    companion object : KLogging()
    private val redisServer = RedisServer(redisProperties.port)

    @PostConstruct
    fun postConstruct() {
        logger.info { "== redis-server start" }
        redisServer.start();
    }

    @PreDestroy
    fun preDestroy() {
        logger.info { "== redis-server stop" }
        redisServer.stop();
    }
}