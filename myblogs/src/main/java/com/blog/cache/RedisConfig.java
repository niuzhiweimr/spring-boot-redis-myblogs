package com.blog.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

//缓存层redis配置
@Configuration
@EnableCaching
@Component("redisConfig")
public class RedisConfig extends CachingConfigurerSupport {

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

		RedisSerializer<String> redisSerializer = new StringRedisSerializer();

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		template.setConnectionFactory(factory);
		// key序列化方式
		template.setKeySerializer(redisSerializer);
		// value序列化
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// value hashmap序列化
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		return template;
	}

	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {

		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		Map<String, Long> expires = new HashMap<String, Long>();
		// 设置key的过期时间
		expires.put("24h", 3600 * 24L);
		expires.put("12h", 3600 * 12L);
		expires.put("1h", 3600 * 1L);
		//300秒
		expires.put("300s", 60 * 5L);
		//10秒
		expires.put("10s", 10 * 1L);
		cacheManager.setExpires(expires);
		return cacheManager;
	}
}
