package com.example.springbootconcurrencybasis.domain.booking.repository

import com.example.springbootconcurrencybasis.domain.booking.model.Booking
import com.example.springbootconcurrencybasis.domain.config.redis.MyRedisConnectionInfo
import com.example.springbootconcurrencybasis.global.exception.ErrorCode
import com.example.springbootconcurrencybasis.global.exception.detail.SystemRuntimeException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class BookingRedisRepository(
    @Qualifier(value = MyRedisConnectionInfo.BOOKING_TEMPLATE)
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun increaseBooking(booking: Booking, bookingCount: Int): Long {
        val key = generateKey(booking)
        redisTemplate.opsForValue().setIfAbsent(key, bookingCount.toString(), Duration.ofMinutes(5))
        return redisTemplate.opsForValue()
            .increment(key) ?: throw SystemRuntimeException(ErrorCode.SR100, "레디스 incr command 상에서 문제가 발생하였습니다.")
    }

    fun decreaseBooking(booking: Booking): Long? {
        val key = generateKey(booking)
        return redisTemplate.opsForValue()
            .decrement(key)
    }

    fun watchBooking(booking: Booking) {
        val key = generateKey(booking)
        redisTemplate.execute { redisConnection ->
            redisConnection.watch(key.toByteArray())
            redisConnection.set(key.toByteArray(), 0.toString().toByteArray())
            redisConnection.multi()
            redisConnection.incr(key.toByteArray())
            redisConnection.exec()
        }
    }

    fun get(booking: Booking): Int {
        val key = generateKey(booking)
        return redisTemplate.opsForValue()
            .get(key)?.toInt() ?: 0
    }

    private fun generateKey(booking: Booking): String {
        return "BOOKING-CONCERT:${booking.concert!!.id}:BOOKING-TICKET:${booking.ticket!!.id}"
    }
}