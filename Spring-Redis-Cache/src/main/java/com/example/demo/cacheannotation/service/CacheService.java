package com.example.demo.cacheannotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> template;

    @Value("${spring.redis.cache.enabled:true}")
    private boolean cacheEnabled;

    public void clearCache(){
        template.execute((RedisCallback<Object>) var1 -> {
            var1.flushAll();
            return null;
        });
    }

    public <T> T cacheGet(String key, Class<T> returntype){
        if(!cacheEnabled) return null;
        return (T) template.opsForValue().get(key);
    }

    public void cachePut(String key, Object returnObject, int ttlMinutes) {
        if(!cacheEnabled) return;

        template.opsForValue().set(key, returnObject, ttlMinutes, TimeUnit.MINUTES);
    }

    public void cachePut(String key, Object returnObject) {
        if(!cacheEnabled) return;
        if(returnObject==null) return;
        template.opsForValue().set(key, returnObject);
    }
}
