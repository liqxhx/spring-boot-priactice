package com.lqh.practice.springboot.cache.simple;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> 类描述: SimpleCacheManagerConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 22:17
 * @since 2021/05/26 22:17
 */
@Configuration
@EnableCaching
public class SimpleCacheManagerConfiguration {
    @Bean
    @Primary
    public SimpleCacheManager simpleCacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

//        ConcurrentMapCache usersCache = new ConcurrentMapCache("users");
//        ConcurrentMapCache defaultCache = new ConcurrentMapCache("default");
//
//        List<Cache> caches = new ArrayList<>(2);
//        caches.add(usersCache);
//        caches.add(defaultCache);
//
//        simpleCacheManager.setCaches(caches);

        simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("users"), new ConcurrentMapCache("default")));
        return simpleCacheManager;

    }

    @Bean
    public NoOpCacheManager noOpCacheManager() {
        return new NoOpCacheManager();
    }

    @Bean
    public ConcurrentMapCacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @Bean
    public CompositeCacheManager compositeCacheManager() {
        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
        compositeCacheManager.setCacheManagers(Arrays.asList(simpleCacheManager()));
        return compositeCacheManager;
    }

    @Bean
    public KeyGenerator userKeyGenerator() {
        return new KeyGenerator() {
            /**
             *
             * @param target UserService.class
             * @param method
             * @param params
             * @return
             */
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return target.getClass().getSimpleName().concat(":" + String.valueOf(params[0]));
            }
        };
    }
}
