package com.lqh.practice.springboot.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.lqh.practice.springboot.cache.common.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <p> 类描述: CaffeineRunner
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/27 22:57
 * @since 2021/05/27 22:57
 */
@SpringBootApplication
public class CaffeineRunner implements ApplicationRunner {
    @Autowired
    CaffeineUserService userService;

    @Autowired
    CacheManager cacheManager;

    /**
     * main
     */
    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(CaffeineRunner.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user);

        user = userService.getUser(1L);
        System.out.println(user);
    }
}

@Service
class CaffeineUserService {
    @Cacheable(cacheNames = "userGuavaCache", key="#id")
    public User getUser(Long id) {
        System.out.println("查询 User:" + id);
        return loadFromDB(id);
    }

    private User loadFromDB(Long id) {
        System.out.println("从数据库加载 User:" + id);
        return User.builder().id(id).build();
    }
}

enum CacheEnum {
    /**
     * @date 16:34 2020/10/27
     * 第一个cache
     **/
    FIRST_CACHE(300, 20000, 300),
    /**
     * @date 16:35 2020/10/27
     * 第二个cache
     **/
    SECOND_CACHE(60, 10000, 200);

    @Getter
    private int second;
    @Getter
    private long maxSize;
    @Getter
    private int initSize;

    CacheEnum(int second, long maxSize, int initSize) {
        this.second = second;
        this.maxSize = maxSize;
        this.initSize = initSize;
    }
}

@Configuration
@EnableCaching
class CaffeineCacheConfiguration {
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(60, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000));
        return cacheManager;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caffeineCaches = new ArrayList<>();
        for (CacheEnum cacheEnum : CacheEnum.values()) {
            caffeineCaches.add(new CaffeineCache(cacheEnum.name(),
                    Caffeine.newBuilder().expireAfterWrite(Duration.ofSeconds(cacheEnum.getSecond()))
                            .initialCapacity(cacheEnum.getInitSize())
                            .maximumSize(cacheEnum.getMaxSize()).build()));
        }
        cacheManager.setCaches(caffeineCaches);
        return cacheManager;
    }
}
