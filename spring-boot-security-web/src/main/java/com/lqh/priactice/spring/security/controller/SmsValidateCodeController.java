package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.dto.ResponseDTO;
import com.lqh.priactice.spring.security.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> 类描述: SmsValidateCodeController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/11 09:00
 * @since 2020/09/11 09:00
 */
@Controller
@RequestMapping("/validate/code")
@Slf4j
public class SmsValidateCodeController {
    @Autowired
    private SmsService smsService;

    /**
     * http://localhost:8080/validate/code/sms/send/15888888888
     * @param mobile
     */
    @RequestMapping(value = "/sms/send/{mobile}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseDTO<String> sendSms(@PathVariable(name="mobile") String mobile) {
        // todo 生成短信验证码
        // todo 将验证码放到session中 SessionStrategy
        // todo 放送验证码
        log.info("/validate/code/sms/send/{}", mobile);
        return ResponseDTO.success(smsService.generateAndSend(mobile).getCode());
    }
}
