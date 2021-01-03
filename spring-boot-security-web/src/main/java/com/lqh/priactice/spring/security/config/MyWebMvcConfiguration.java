package com.lqh.priactice.spring.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p> 类描述: WebMvcConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/31 15:55
 * @since 2020/07/31 15:55
 */
@Slf4j
@Component
public class MyWebMvcConfiguration /*extends WebMvcConfigurationSupport */ implements WebMvcConfigurer {
    /**
     * 异步配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //获取到服务器的cpu内核
        int i = Runtime.getRuntime().availableProcessors();
        //核心池大小
        executor.setCorePoolSize(5);
        //最大线程数
        executor.setMaxPoolSize(i);
        //队列程度
        executor.setQueueCapacity(1000);
        //线程空闲时间
        executor.setKeepAliveSeconds(1000);
        //线程前缀名称
        executor.setThreadNamePrefix("task-async-");
        //配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        configurer.setTaskExecutor(executor);
    }

    /**
     * 静态文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "classpath:/error/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrorPageRegistrar() {
            @Override
            public void registerErrorPages(ErrorPageRegistry registry) {
//                registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/404.html"));
                registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            }
        };
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,
                    "/error/404");
            ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,
                    "/error/500");
            ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                    "/error/500");
            factory.addErrorPages(errorPage400, errorPage404,
                    errorPage500);
        };
    }
}
