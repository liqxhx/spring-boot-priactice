package com.lqh.practice.springboot.cache.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 类描述: User
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/05/26 22:15
 * @since 2021/05/26 22:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
}
