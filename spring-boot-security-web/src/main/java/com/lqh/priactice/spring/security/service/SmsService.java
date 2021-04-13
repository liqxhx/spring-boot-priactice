package com.lqh.priactice.spring.security.service;

import com.lqh.priactice.spring.security.exception.CaptchaException;
import com.lqh.priactice.spring.security.model.SmsValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p> 类描述: SmsService
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/11 09:01
 * @since 2020/09/11 09:01
 */
@Service
@Component
@Slf4j
public class SmsService {
    private static final int EXPIRE_SECONDS = 60;
    private Map<String, SmsValidateCode> smsMap = new HashMap<String, SmsValidateCode>(8);
    @Async
    public SmsValidateCode generateAndSend(String mobile) {
        log.debug("#service#sms#mobile:{}", mobile);
        SmsValidateCode smsValidateCode = generateSmsValidateCode(mobile);
        send(smsValidateCode);
        return smsValidateCode;
    }

    private void send(SmsValidateCode smsValidateCode) {
        log.info("#service#sms#send:{}", smsValidateCode);
        smsMap.put(smsValidateCode.getMobile(), smsValidateCode);
    }

    private SmsValidateCode generateSmsValidateCode(String mobile) {
        return new SmsValidateCode(RandomStringUtils.randomNumeric(6), mobile,  120);
    }

    @Scheduled(cron = "*/1 * * * * ?")
//	@Async 不用等待任务执行完成即可重新执行
    public void check() throws InterruptedException {
        log.debug("checking. size:{}", smsMap.size());
        Thread.sleep(10000);
        Iterator<Map.Entry<String, SmsValidateCode>> it = smsMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, SmsValidateCode> entry = it.next();
            SmsValidateCode value = entry.getValue();
            if(needRemove(value)) {
                it.remove();
                log.info("remove:{}", value);
            }
        }
    }

    private boolean needRemove(SmsValidateCode imageValidateCode) {
        return LocalDateTime.now().isAfter(imageValidateCode.getExpireTime().plusSeconds(2 * EXPIRE_SECONDS));
    }

    public void validate(String mobile, String smsValidateCode) throws CaptchaException {
        log.debug("#service#sms#mobile:{}, validateCode:{}", mobile, smsValidateCode);
        if(StringUtils.isBlank(smsValidateCode)) {
            throw new CaptchaException("验证码不能为空");
        }

        SmsValidateCode code = smsMap.get(mobile);

        if (code == null || StringUtils.isBlank(code.getCode())) {
            throw new CaptchaException("验证码信息"+smsValidateCode);
        }

        if(code.isExpired()) {
            throw new CaptchaException("验证码已过期");
        }

        if (code.getCode().equals(smsValidateCode)) {
            smsMap.remove(smsValidateCode);
            return;
        }

        throw new CaptchaException("验证码不匹配");
    }
}
