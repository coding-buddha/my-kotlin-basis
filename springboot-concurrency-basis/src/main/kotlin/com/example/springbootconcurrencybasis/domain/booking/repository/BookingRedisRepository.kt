package com.example.springbootconcurrencybasis.domain.booking.repository

import com.example.springbootconcurrencybasis.domain.booking.model.Booking
import com.example.springbootconcurrencybasis.domain.config.redis.MyRedisConnectionInfo
import com.example.springbootconcurrencybasis.global.exception.ErrorCode
import com.example.springbootconcurrencybasis.global.exception.detail.SystemRuntimeException
import mu.KLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.connection.RedisStringCommands
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.types.Expiration
import org.springframework.stereotype.Repository
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.random.Random

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

        logger.info { "key : $key" }

        val result = redisTxTemplate.execute { redisConnection ->
            try {
                redisConnection.watch(key.toByteArray())

                redisConnection.multi()
                redisConnection.set(key.toByteArray(), booking.id.toString().toByteArray(),
                    Expiration.from(Duration.ofMinutes(5)),
                    RedisStringCommands.SetOption.SET_IF_ABSENT
                )
                redisConnection.exec()
                "Hello"
            } catch (exception: Exception) {
                logger.error { "에러가 발생 : ${exception.message}" }
            }
        }

        logger.info { "result : $result, value : ${redisTxTemplate.opsForValue().get(key)}" }
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