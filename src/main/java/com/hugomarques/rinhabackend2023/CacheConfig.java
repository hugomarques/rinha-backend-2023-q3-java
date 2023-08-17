package com.hugomarques.rinhabackend2023;

import com.hugomarques.rinhabackend2023.pessoas.Pessoa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<String, Pessoa> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Pessoa> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}
