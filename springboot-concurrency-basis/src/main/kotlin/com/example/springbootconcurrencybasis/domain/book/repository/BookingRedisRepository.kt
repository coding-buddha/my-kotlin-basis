package com.example.springbootconcurrencybasis.domain.book.repository

import com.example.springbootconcurrencybasis.domain.config.redis.MyRedisConnectionInfo
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class BookingRedisRepository(
    @Qualifier(value = MyRedisConnectionInfo.BOOKING_TEMPLATE)
    private val redisTemplate: RedisTemplate<String, Int>
) {

    companion object {
        const val BOOKING_KEY = "BOOKING"
    }

    fun save(id: String, value: Int): Boolean? {
        val key = "$BOOKING_KEY:$id"
        return redisTemplate.opsForValue()
            .setIfAbsent(key, value, Duration.ofMinutes(5))
    }

    fun get(id: String): Int? {
        val key = "$BOOKING_KEY:$id"
        return redisTemplate.opsForValue()
            .get(key)
    }
}