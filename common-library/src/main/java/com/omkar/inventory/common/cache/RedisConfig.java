package com.omkar.inventory.common.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template =
                new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);

        ObjectMapper mapper = new ObjectMapper();

        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(mapper);

        template.setKeySerializer(new StringRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(serializer);

        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public CacheManager cacheManager(
            RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)

                .cacheDefaults(defaultConfig)

                .withInitialCacheConfigurations(Map.of(

                        CacheNames.PRODUCTS,
                        defaultConfig.entryTtl(Duration.ofMinutes(CacheConstants.PRODUCTS_TTL)),

                        CacheNames.USERS,
                        defaultConfig.entryTtl(Duration.ofMinutes(CacheConstants.USERS_TTL)),

                        CacheNames.INVENTORY,
                        defaultConfig.entryTtl(Duration.ofMinutes(CacheConstants.INVENTORY_TTL)),

                        CacheNames.SUPPLIERS,
                        defaultConfig.entryTtl(Duration.ofMinutes(CacheConstants.SUPPLIERS_TTL)),

                        CacheNames.PURCHASES,
                        defaultConfig.entryTtl(Duration.ofMinutes(CacheConstants.PURCHASES_TTL))

                ))

                .build();

    }

}