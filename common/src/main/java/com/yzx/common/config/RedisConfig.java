package com.yzx.common.config;

/**
 * @className: RedisConfig
 * @author: yzx
 * @date: 2025/8/29 12:10
 * @Version: 1.0
 * @description:
 */


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类，解决序列化乱码问题
 */
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate，设置合适的序列化器
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // JSON 序列化器（处理 Value）
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();

        // 配置序列化可见性（所有属性都可序列化）
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 关键：Jackson 2.9.x 用 enableDefaultTyping 开启类型信息（旧版 API）
        // 替代新版的 activateDefaultTyping + LaissezFaireSubTypeValidator
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 字符串序列化器（处理 Key，避免乱码）
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 配置 Key/Value 序列化器
        template.setKeySerializer(stringRedisSerializer);         // 普通 Key 用字符串序列化
        template.setHashKeySerializer(stringRedisSerializer);     // Hash Key 用字符串序列化
        template.setValueSerializer(jackson2JsonRedisSerializer); // 普通 Value 用 JSON 序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer); // Hash Value 用 JSON 序列化

        template.afterPropertiesSet();
        return template;
    }
}
