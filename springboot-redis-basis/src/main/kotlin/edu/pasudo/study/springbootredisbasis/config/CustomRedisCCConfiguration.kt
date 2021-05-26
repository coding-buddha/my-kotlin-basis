package edu.pasudo.study.springbootredisbasis.config

import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.DATABASE_CC
import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.FACTORY_CC
import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.REDIS_CC
import mu.KLogging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class CustomRedisCCConfiguration(
    private val redisProperties: RedisProperties
) {

    companion object : KLogging()

    @Bean(name = [FACTORY_CC])
    fun redisConnectionFactory(): LettuceConnectionFactory {
        logger.info {"==> CC :: redisConnectionFactory"}
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
            .apply {
                database = DATABASE_CC
            }
    }

    @Bean(name = [REDIS_CC])
    fun redisTemplateOnBBProperty(): RedisTemplate<String, Any> {
        logger.info {"==> CC :: redisTemplate"}
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}