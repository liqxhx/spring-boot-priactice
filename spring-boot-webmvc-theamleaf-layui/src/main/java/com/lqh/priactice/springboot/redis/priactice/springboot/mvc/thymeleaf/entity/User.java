package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.entity;

import com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 类描述: User
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/10 17:06
 * @since 2020/07/10 17:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String userName;
    private int age;
    private String password;
    private String password2;
    private Gender gender;
    private String iconFace;
    private int page;
    private int limit;

}
