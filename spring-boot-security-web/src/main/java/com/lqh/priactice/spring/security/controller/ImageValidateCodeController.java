package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.model.ImageValidateCode;
import com.lqh.priactice.spring.security.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 类描述: ValidateCodeController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/09 22:12
 * @since 2020/09/09 22:12
 */
@Controller
@RequestMapping("/validate/code")
@Slf4j
public class ImageValidateCodeController {
    @Autowired
    private CaptchaService captchaService;

    /**
     * /validate/code/image/refresh
     * @param response
     * @throws IOException
     */
    @GetMapping("/image/refresh")
    public void generateImageCode(HttpServletResponse response) throws IOException {
        log.info("/validate/code/image/refresh");
        ImageValidateCode imageValidateCode = captchaService.generate();
        ImageIO.write(imageValidateCode.getImage(), "jpeg", response.getOutputStream());
    }
}
