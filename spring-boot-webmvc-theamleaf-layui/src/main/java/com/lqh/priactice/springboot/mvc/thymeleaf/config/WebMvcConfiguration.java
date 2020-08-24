package com.lqh.priactice.springboot.mvc.thymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p> 类描述: WebMvcConfiguration
 *
 * @author liqinghui
 * @version 1.0
 * @date 2020/05/15 11:47
 * @since 2020/05/15 11:47
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/template/",  "classpath:/resources/");
    }
}
