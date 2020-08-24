package com.lqh.priactice.springboot.mvc.thymeleaf.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * <p> 类描述: I18nConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/17 00:01
 * @since 2020/07/17 00:01
 */
@Configuration
public class I18nConfiguration {
    @Bean
    public MessageSource errorCodeMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("utf-8");
        resourceBundleMessageSource.setBasenames("i18n/error_code");
        return resourceBundleMessageSource;
    }
}
