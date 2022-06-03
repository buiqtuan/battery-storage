package com.spring.fun.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.fun.service.BatteryCachingService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class BeanContainer {

    @Value("${cache.name.app}")
    private String CACHE_NAME;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(this.CACHE_NAME);
    }

    @Bean
    public BatteryCachingService batteryCachingService() {
        return new BatteryCachingService(this.cacheManager());
    }
}
