# springboot-redis-basis

## run
```shell
// redis standalone 실행
$ docker-compose up -d
```

## study
* 로깅을 편하게 하고싶다.
    * https://github.com/MicroUtils/kotlin-logging
    * https://amarszalek.net/blog/2018/05/13/logging-in-kotlin-right-approach/
  
## error 1
Parameter 0 of method redisTemplate in org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration required a single bean, but 3 were found:
> RedisAutoConfiguration 자체도 빈인데, 내부적으로 RedisConnectionFactory 를 주입받는다. 이 때, 해당 주입받는 빈이 여러개인 경우에 충돌이 나기 때문에 RedisConnectionFactory 를 여러개 빈으로 등록해두었다면 하나정도는 `@Primary` 를 붙여서 우선순위조정을 해주어야 한다.
```java
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })
public class RedisAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

}
```

## error 2
embedded redis 의 버전을 높여주자.
* embedded redis 의 버전은 2.8.19 version 인데 해당 버전에서는 geo operation 이 작동되지 않는다. 따라서 특정 버전으로 embedded redis server 를 실행시켜주어야 한다.
```kotlin
@TestConfiguration
class TestRedisConfiguration(
    redisProperties: RedisProperties,
) {

    companion object : KLogging()

    // geo operation 을 이용해주기 위해서 해당 버전을 올려준다. : redis-server 5.0.12 버전은 해당 프로젝트 내에서 가지고 있어야 한다.
    private val customProvider = RedisExecProvider.defaultProvider()
        .override(OS.MAC_OS_X, Resources.getResource("redis-server-5.0.12").file)

    private val redisServer = RedisServerBuilder()
        .redisExecProvider(customProvider)
        .port(redisProperties.port)
        .build()

    // ...
}
```
* https://download.redis.io/releases/
  * 해당 사이트에서 필요한 레디스 버전을 설치
* https://redis.io/topics/quickstart
  * 해당 사이트를 통해 레디스 서버를 설치 `make` 이후에 컴파일된 실행가능한 redis server 를 프로젝트 내에 임포트 시키고 이용한다.

## reference
* http://redisgate.kr/redisgate/ent/ent_intro.php
* https://github.com/pasudo123/embedded-redis