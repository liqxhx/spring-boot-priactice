package com.lqh.practice.springboot.cache.ehcache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * <p> 类描述: EhcacheCacheManagerRunner
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 23:53
 * @since 2021/05/26 23:53
 */
@SpringBootApplication
public class EhcacheCacheManagerRunner {
}

@Configuration
class EhcacheCacheManagerConfiguration {
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheCacheManager;
    }
}
