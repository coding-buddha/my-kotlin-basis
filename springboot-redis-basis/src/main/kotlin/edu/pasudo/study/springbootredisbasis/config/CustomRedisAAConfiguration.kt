package edu.pasudo.study.springbootredisbasis.config

import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.DATABASE_AA
import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.FACTORY_AA
import edu.pasudo.study.springbootredisbasis.config.CustomRedisInfo.REDIS_AA
import mu.KLogging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class CustomRedisAAConfiguration(
    private val redisProperties: RedisProperties
) {

    companion object : KLogging()

    /**
     * RedisAutoConfiguration 에서 LettuceConnectionFactory 타입에 대해서
     * 하나의 빈만 주입받고 있기 때문에 여러개의 Factory 를 빈으로 등록하면 에러가 발생한다.
     * 따라서, @Primary 를 통해 우선순위를 부여하여 의존받는 빈은 하나로 선정하고 커스텀하게는 여러개의 Factory 로 레디스 템플릿을 설정할 수 있도록 한다.
     */
    @Primary
    @Bean(name = [FACTORY_AA])
    fun redisConnectionFactory(): LettuceConnectionFactory {
        logger.info {"==> AA :: redisConnectionFactory"}
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
            .apply {
                database = DATABASE_AA
            }
    }

    @Bean(name = [REDIS_AA])
    fun redisTemplateOnAAProperty(): RedisTemplate<String, Any> {
        logger.info {"==> AA :: redisTemplate"}
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}