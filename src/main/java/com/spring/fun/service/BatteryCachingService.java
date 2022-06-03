package com.spring.fun.service;

import com.spring.fun.entity.Battery;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;

import java.util.*;

public class BatteryCachingService {

    private final CacheManager cacheManager;

    public BatteryCachingService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @CachePut(value = "appCache", key = "'battery'")
    public List<Battery> createBatteries(List<Battery> batteries) {

        return batteries;
    }

    public List<Battery> getBatteries() {
        try {
            var batteries = (List<Battery>) this.cacheManager.getCache("appCache").get("battery").get();

            Collections.sort(batteries, Comparator.comparing(Battery::getLowerCaseName));

            return batteries;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
