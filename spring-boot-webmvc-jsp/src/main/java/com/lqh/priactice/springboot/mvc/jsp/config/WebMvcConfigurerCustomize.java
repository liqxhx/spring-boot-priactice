package com.lqh.priactice.springboot.mvc.jsp.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * <p> 类描述: TODO
 *
 * @author liqxhx
 * @version TODO
 * @date 2020/03/15 15:58
 * @since 2020/03/15 15:58
 */
@Configuration
public class WebMvcConfigurerCustomize implements WebMvcConfigurer {
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
