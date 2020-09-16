package com.lqh.priactice.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * <p> 类描述: 简单的图片验证码
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/09 22:05
 * @since 2020/09/09 22:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageValidateCode extends SimpleValidateCode {
    private BufferedImage image;

    public ImageValidateCode(BufferedImage image, String code, int expireAfterSeconds) {
        setExpireTime(LocalDateTime.now().plusSeconds(expireAfterSeconds));
        setCode(code);
        this.image = image;
    }

}
