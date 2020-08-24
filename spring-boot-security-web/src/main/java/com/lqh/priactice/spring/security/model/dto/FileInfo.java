package com.lqh.priactice.spring.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 类描述: FileInfo
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/10 07:43
 * @since 2020/07/10 07:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String path;
}
