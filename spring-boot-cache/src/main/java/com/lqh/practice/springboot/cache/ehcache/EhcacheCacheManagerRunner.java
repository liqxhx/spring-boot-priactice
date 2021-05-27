package com.lqh.practice.springboot.cache.ehcache;

import com.lqh.practice.springboot.cache.common.User;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p> 类描述: EhcacheCacheManagerRunner
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 23:53
 * @since 2021/05/26 23:53
 */
@SpringBootApplication
@EnableCaching
public class EhcacheCacheManagerRunner {
    /**
    * main
    */
    public static void main(String[] args){
        // 指定配置文件为xehcache.properties xehcache.yml
        System.setProperty("spring.config.name", "xehcache");
        SpringApplication springApplication = new SpringApplication(EhcacheCacheManagerRunner.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}

@Component
class Runner implements ApplicationRunner {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private EhcacheUserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user);

        user = userService.getUser(1L);
        System.out.println(user);
    }
}


@Service
class EhcacheUserService {
    @Cacheable(cacheNames = "usersEhCache", key="#id")
    public User getUser(Long id) {
        System.out.println("查询 User:" + id);
        return loadFromDB(id);
    }

    private User loadFromDB(Long id) {
        System.out.println("从数据库加载 User:" + id);
        return User.builder().id(id).build();
    }
}

@Configuration
class EhcacheCacheManagerConfiguration {
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return factoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager manager) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(manager);
        return ehCacheCacheManager;
    }
}
