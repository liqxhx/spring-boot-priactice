package com.lqh.practice.spi.driver.xs;

import com.lqh.practice.spi.driver.SoftSwitchOperations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p> 类描述: SoftSwitchOperations XS Implemention
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/27 21:27
 * @since 2021/01/27 21:27
 */
public class XsOmOperations implements SoftSwitchOperations {
    /**
     *
     * @param ping
     * @return
     */
    @Override
    public String ping(String ping) {
        return "pong ".concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
