package edu.pasudo.study.springbootredisbasis.geo

import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo
import mu.KLogging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

/**
 * https://daddyprogrammer.org/post/4056/reactive-redis/
 */
@Configuration
class GeoRedisConfiguration(
    private val redisProperties: RedisProperties
) {

    companion object : KLogging()

    @Bean(name = [CustomRedisInfo.GEO_FACTORY])
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
            .apply {
                database = CustomRedisInfo.GEO_DATBASE
            }
    }

    @Bean(name = [CustomRedisInfo.GEO_TEMPLATE])
    fun redisGeoTemplate(): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}