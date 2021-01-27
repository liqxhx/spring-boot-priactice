package com.lqh.practice.spi.driver.demo.config;

import com.lqh.practice.spi.driver.SoftSwitchOperations;
import com.lqh.practice.spi.driver.xs.XsOmOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> 类描述: SoftSwitchOperationsConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/27 21:48
 * @since 2021/01/27 21:48
 */
@Configuration
public class SoftSwitchOperationsConfiguration {
    @Bean
    public SoftSwitchOperations xsOmOperations() {
        return new XsOmOperations();
    }
}
