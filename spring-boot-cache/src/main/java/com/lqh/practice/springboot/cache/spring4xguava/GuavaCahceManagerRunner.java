package com.lqh.practice.springboot.cache.spring4xguava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lqh.practice.springboot.cache.common.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <p> 类描述: GuavaCahceManagerRunner
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/27 00:00
 * @since 2021/05/27 00:00
 */
@SpringBootApplication
public class GuavaCahceManagerRunner implements CommandLineRunner {
    @Autowired
    GuavaUserService userService;

    @Autowired
    CacheManager cacheManager;

    /**
    * main
    */
    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(GuavaCahceManagerRunner.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user);

        user = userService.getUser(1L);
        System.out.println(user);
    }
}


@Configuration
@EnableCaching
@EnableConfigurationProperties(GuavaProperties.class)
class GuavaCacheManagerConfiguration {
    private static final int DEFAULT_MAXSIZE = 1000;
    private static final int DEFAULT_TTL = 3600;

    @Autowired
    GuavaProperties guavaProperties;

    // 方式一
    @Bean
    public GuavaCacheManager guavaCacheManager() {
       return new GuavaCacheManager();
    }

    // 方式二 enum
    @Bean
    @Primary
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        //把各个cache注册到cacheManager中，GuavaCache实现了org.springframework.cache.Cache接口
        ArrayList<GuavaCache> caches = new ArrayList<>();
        for (Caches c : Caches.values()) {
            caches.add(new GuavaCache(c.name(), CacheBuilder.newBuilder().recordStats().expireAfterWrite(c.getTtl(), TimeUnit.SECONDS).maximumSize(c.getMaxSize()).build()));
        }
        manager.setCaches(caches);
        return manager;
    }

    // 方式三  guavaProperties
    @Bean
    public CacheBuilder<Object, Object> cacheBuilder() {
        long maximumSize = guavaProperties.getMaximumSize();
        long duration = guavaProperties.getExpireAfterAccessDuration();
        if (maximumSize <= 0) {
            maximumSize = 1024;
        }
        if (duration <= 0) {
            duration = 1800;
        }
        return CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(duration, TimeUnit.HOURS);
    }

    @DependsOn({"cacheBuilder"})
    @Bean
    public Cache newCache(CacheBuilder<Object, Object> cacheBuilder) {
        return cacheBuilder.build();
    }

    enum Caches {

        user(60, 2),
        info(5),
        role;

        Caches() {
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        private int maxSize = DEFAULT_MAXSIZE;    //最大數量
        private int ttl = DEFAULT_TTL;        //过期时间（秒）

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }
    }
}


@Service
class GuavaUserService {
    @Resource(name="newCache")
    private Cache<Integer, User> cache;

    @Cacheable(cacheNames = "user", key="#id")
    public User getUser(Long id) {
        System.out.println("查询 User:" + id);
        return loadFromDB(id);
    }

    private User loadFromDB(Long id) {
        System.out.println("从数据库加载 User:" + id);
        return User.builder().id(id).build();
    }
}


@Component
@ConfigurationProperties(prefix = "guava.cache.config")
@Data
class GuavaProperties {

    private long maximumSize;

    private long maximumWeight;

    private long expireAfterWriteDuration;

    private long expireAfterAccessDuration;

    private long refreshDuration;


    private int initialCapacity;

    private int concurrencyLevel;
}
