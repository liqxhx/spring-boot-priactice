package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.jsp.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * <p> 类描述: WebMvcConfigurerCustomize
 *
 * @author liqxhx
 * @version 1.0
 * @date 2020/03/15 15:58
 * @since 2020/03/15 15:58
 */
@Configuration
public class WebMvcConfigurerCustomize implements WebMvcConfigurer {

//    @Bean
//    public ViewResolver myViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/pages/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setCache(false);
//
//        // 优先级高于 ThymeleafViewResolver
//        // ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE - 5
//        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
//
//        // 配置 ViewResolver 的 Content-Type // text/xml;charset=UTF-8
//        viewResolver.setContentType("text/xml");
//        return viewResolver;
//    }

    /**
     * 解决 Maven 多模块 JSP 无法读取的问题
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return (factory) -> {
            factory.addContextCustomizers((context) -> {
                        String relativePath = "spring-boot-webmvc-jsp/src/main/webapp";
                        File docBaseFile = new File(relativePath);
                        if(docBaseFile.exists()) {
                            context.setDocBase(docBaseFile.getAbsolutePath());
                        }
                    }
            );
        };
    }
}
