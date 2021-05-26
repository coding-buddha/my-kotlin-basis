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


## reference
* http://redisgate.kr/redisgate/ent/ent_intro.php