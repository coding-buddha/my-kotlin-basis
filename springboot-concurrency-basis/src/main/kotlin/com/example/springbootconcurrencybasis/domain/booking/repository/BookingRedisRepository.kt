package com.example.springbootconcurrencybasis.domain.booking.repository

import com.example.springbootconcurrencybasis.domain.booking.model.Booking
import com.example.springbootconcurrencybasis.domain.config.redis.MyRedisConnectionInfo
import com.example.springbootconcurrencybasis.global.exception.ErrorCode
import com.example.springbootconcurrencybasis.global.exception.detail.SystemRuntimeException
import mu.KLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.connection.RedisConnection
import org.springframework.data.redis.core.RedisCallback
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class BookingRedisRepository(
    @Qualifier(value = MyRedisConnectionInfo.BOOKING_TEMPLATE)
    private val redisTemplate: RedisTemplate<String, String>,
    @Qualifier(value = MyRedisConnectionInfo.BOOKING_TX_TEMPLATE)
    private val redisTxTemplate: RedisTemplate<String, String>
) {

    companion object : KLogging()

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

        logger.info { "[1] key : $key" }
        redisTxTemplate.opsForValue().setIfAbsent(key, 0.toString(), Duration.ofMinutes(5))
        val lastCount = redisTxTemplate.opsForValue().get(key)?.toInt() ?: 0
        logger.info { "[2] lastCount : $lastCount" }

        val result = redisTxTemplate.execute { redisConnection ->
            try {
                redisConnection.watch(key.toByteArray())
                redisConnection.set(key.toByteArray(), 0.toString().toByteArray())
                redisConnection.multi()
                redisConnection.incr(key.toByteArray())
                redisConnection.exec()
                "Hello"
            } catch (exception: Exception) {
                logger.error { "에러가 발생 : ${exception.message}" }
            }
        }

        logger.info { "[3] result : $result, value : ${redisTxTemplate.opsForValue().get(key)}" }
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