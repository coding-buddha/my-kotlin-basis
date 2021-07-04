package com.example.springbootconcurrencybasis.domain.book.repository

import com.example.springbootconcurrencybasis.domain.book.service.BookingChecker
import com.example.springbootconcurrencybasis.domain.config.redis.MyRedisConnectionInfo
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class BookingRedisRepository(
    @Qualifier(value = MyRedisConnectionInfo.BOOKING_TEMPLATE)
    private val redisTemplate: RedisTemplate<String, String>
) {

    companion object {
        const val BOOKING_KEY = "BOOKING"
    }

    fun increaseBooking(id: String, bookingCount: Int): Long {
        val key = generateKey(id)
        redisTemplate.opsForValue().setIfAbsent(key, bookingCount.toString(), Duration.ofMinutes(5))
        return redisTemplate.opsForValue()
            .increment(key) ?: throw Exception("레디스 incr command 상에서 문제가 발생하였습니다.")
    }

    fun decreaseBooking(id: String): Long? {
        val key = generateKey(id)
        return redisTemplate.opsForValue()
            .decrement(key)
    }

    fun get(id: String): Int {
        val key = "$BOOKING_KEY:$id"
        return redisTemplate.opsForValue()
            .get(key)?.toInt() ?: 0
    }

    private fun generateKey(id: String): String {
        return "$BOOKING_KEY:$id"
    }
}