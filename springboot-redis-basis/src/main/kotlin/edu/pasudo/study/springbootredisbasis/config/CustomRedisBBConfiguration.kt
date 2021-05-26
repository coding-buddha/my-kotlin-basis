package edu.pasudo.study.springbootredisbasis.config

import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.DATABASE_BB
import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.FACTORY_BB
import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.REDIS_BB
import mu.KLogging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class CustomRedisBBConfiguration(
    private val redisProperties: RedisProperties
) {

    companion object : KLogging()

    @Bean(name = [FACTORY_BB])
    fun redisConnectionFactory(): LettuceConnectionFactory {
        logger.info {"==> BB :: redisConnectionFactory"}
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
            .apply {
                database = DATABASE_BB
            }
    }

    @Bean(name = [REDIS_BB])
    fun redisTemplateOnBBProperty(): RedisTemplate<String, Any> {
        logger.info {"==> BB :: redisTemplate"}
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}