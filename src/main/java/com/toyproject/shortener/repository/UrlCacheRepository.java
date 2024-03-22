package com.toyproject.shortener.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class UrlCacheRepository {
    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> urlCacheOperation;

    public UrlCacheRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.urlCacheOperation = redisTemplate.opsForValue();
    }

    public void save(String shortUrl, String originalUrl){
        urlCacheOperation.set(shortUrl, originalUrl, Duration.ofMinutes(60));
    }

    public String get(String shortUrl){
        return urlCacheOperation.get(shortUrl);
    }

}
