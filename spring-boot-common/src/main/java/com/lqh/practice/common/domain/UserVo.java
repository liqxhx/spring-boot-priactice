package com.lqh.practice.common.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * <p> 类描述: UserVO
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/01/15 20:34
 * @since 2021/01/15 20:34
 */
@Data
@Accessors(chain = true)
public class UserVo {
    private Long id;
    private String username;
    private String password;
    private Integer gender;
    private LocalDate birthday;
    private String createTime;
    private List<UserConfig> config;
    /**
     * // 测试字段
     */
    private String test;

    @Data
    public static class UserConfig {
        private String field1;
        private Integer field2;
    }
}
