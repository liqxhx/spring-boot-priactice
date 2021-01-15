package com.lqh.practice.common.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p> 类描述: User
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 20:28
 * @since 2021/01/15 20:28
 */
@Data
/**
 *chain的中文含义是链式的，设置为true，则setter方法返回当前对象
 */
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer sex;
    private LocalDate birthday;
    private LocalDateTime createTime;
    /**
     * 其他扩展信息，以JSON格式存储
     */
    private String config;
    private String test;
}
