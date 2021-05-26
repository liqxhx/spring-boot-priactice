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
@Order(3) // 在env中配置context.initializer.classes=com.liqh.practice.springboot.initializer.ThirdInitializer时无效
public class ThirdInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        Map<String, Object> prop = new HashMap<>(8);
        prop.put("key3", "value3");

        String initializer3 = "Initializer333333333";
        MapPropertySource propSource = new MapPropertySource(initializer3, prop);
        environment.getPropertySources().addLast(propSource);

        log.info(initializer3);

    }
}
