package com.example.springbootconcurrencybasis.domain.config.redis

import mu.KLogging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class MyRedisConfiguration(
    private val redisProperties: RedisProperties
) {

    companion object : KLogging()

    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

    @Bean(name = [MyRedisConnectionInfo.TICKET_TEMPLATE])
    fun ticketRedisTemplate(): RedisTemplate<String, Int> {
        return RedisTemplate<String, Int>().apply {
            setConnectionFactory(redisConnectionFactory().apply {
                database = MyRedisConnectionInfo.TICKET_DB
            })
        }
    }

    @Bean(name = [MyRedisConnectionInfo.BOOKING_TEMPLATE])
    fun bookingRedisTemplate(): RedisTemplate<String, Int> {
        return RedisTemplate<String, Int>().apply {
            setConnectionFactory(redisConnectionFactory().apply {
                database = MyRedisConnectionInfo.BOOKING_DB
            })
        }
    }
}