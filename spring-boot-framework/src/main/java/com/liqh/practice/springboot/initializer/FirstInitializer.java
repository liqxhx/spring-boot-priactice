package com.liqh.practice.springboot.initializer;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 类描述: FirstInitializer
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/20 22:12
 * @since 2021/05/20 22:12
 */
@Log
@Order(1)
public class FirstInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        Map<String, Object> prop = new HashMap<>(8);
        prop.put("key1", "value1");

        String initializer11111111 = "Initializer11111111";
        MapPropertySource propSource = new MapPropertySource(initializer11111111, prop);
        environment.getPropertySources().addLast(propSource);
        log.info(initializer11111111);
    }
}
