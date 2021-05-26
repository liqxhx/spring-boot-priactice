package com.liqh.practice.springboot.initializer;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

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
@Order(2)
public class SecondInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        Map<String, Object> prop = new HashMap<>(8);
        prop.put("key2", "value2");

        String initializer2 = "Initializer222222222";
        MapPropertySource propSource = new MapPropertySource(initializer2, prop);
        environment.getPropertySources().addLast(propSource);

        log.info(initializer2);

    }
}
