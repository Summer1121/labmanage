package com.ncepu.feilong505.LabManage.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.maven.model.Build;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ncepu.feilong505.LabManage.service.impl.CourseUserServiceImpl;

/**
 * TODO
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年4月1日
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * 
	 * TODO 获取一个template模板实例
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月1日
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(keySerializer());
		template.setValueSerializer(valueSerializer());
		template.setHashKeySerializer(keySerializer());
		template.setHashValueSerializer(valueSerializer());
		return template;
	}

	/**
	 * 
	 * TODO 生成默认配置的redisManager
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月1日
	 * @param redisConnectionFactory
	 * @return
	 */
//	@Bean
//	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//		RedisCacheManager cacheManager = RedisCacheManager.create(redisConnectionFactory);
//		return cacheManager;
//	}

	/**
	 * 
	 * TODO 生成自定义配置的redisCacheManager
	 * 
	 * @author xtysummer1121@foxmail.com
	 * @date 2019年4月1日
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		// 设置redismapper配置
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		configuration = configuration
//				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))// 设置key的序列化方式
//				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))// 设置value的序列化方式
				.entryTtl(Duration.ofMinutes(1))// 过期时间为1分钟
				.disableCachingNullValues();// 不缓存空值

		// 初始化一个缓存空间名称集合
		Set<String> cacheNames = new HashSet<>();
		cacheNames.add("warning-info");

		// 对每个缓存空间使用不同配置
		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		configMap.put("warning-info", configuration);
		// 使用第二个配置
//		configMap.put("my-redis-cache2", configuration.entryTtl(Duration.ofSeconds(120)));

		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)// 使用自定义的缓存配置初始化一个cachemanager
				.initialCacheNames(cacheNames)// 初始化缓存名称集合
				.withInitialCacheConfigurations(configMap)// 初始化相关配置 （注意先调用顺序先后）
				.build();
		return cacheManager;
	}

	// 对key和value分别设置序列化方式
	private RedisSerializer<Object> keySerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	private RedisSerializer<Object> valueSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}
}
