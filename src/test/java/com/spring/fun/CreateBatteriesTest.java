package com.spring.fun;

import com.spring.fun.config.BeanContainer;
import com.spring.fun.entity.Battery;
import com.spring.fun.service.BatteryCachingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CreateBatteriesTest {

    @Configuration
    @EnableCaching
    public static class Config {
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("appCache");
        }
    }

    @Autowired
    private CacheManager cacheManager;

    private List<Battery> batteries = new ArrayList<>();

    private BatteryCachingService bcs;

    @Before
    public void beforeTest() {
        bcs = new BatteryCachingService(cacheManager);

        batteries.add(new Battery("AAA", "111", 100l));
        batteries.add(new Battery("BBB", "222", 200l));
        batteries.add(new Battery("CCC", "333", 300l));
    }

    @Test
    public void createBatteries_Test() {
        bcs.createBatteries(batteries);

        var listAfterCreating = bcs.getBatteries();

        Assert.assertNotNull(listAfterCreating);
    }
}
