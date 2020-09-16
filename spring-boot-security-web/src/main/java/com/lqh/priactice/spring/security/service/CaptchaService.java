package com.lqh.priactice.spring.security.service;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import com.lqh.priactice.spring.security.exception.CaptchaException;
import com.lqh.priactice.spring.security.model.ImageValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * kaptcha提供了KaptchaServlet来处理验证码的生成并放入session，
 * 但这方式在如今的分布式、前后端分离、集群的部署条件显然不适用
 *
 */
@Slf4j
@Service
public class CaptchaService {
	private Producer producer;
	private Map<String, ImageValidateCode> captchaMap = new HashMap<String, ImageValidateCode>(8);
	private static final int EXPIRE_SECONDS = 60;

	@PostConstruct
	public void init(){
		// 控制验证码的图片的生成的规则的配置信息都放到了com.google.code.kaptcha.util.Config类中
		Properties kaptchaProperties = new Properties();
		kaptchaProperties.put("kaptcha.border", "no");
		kaptchaProperties.put("kaptcha.textproducer.char.length","4");
		kaptchaProperties.put("kaptcha.image.height","50");
		kaptchaProperties.put("kaptcha.image.width","200");
		kaptchaProperties.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
		kaptchaProperties.put("kaptcha.textproducer.font.color","black");
		kaptchaProperties.put("kaptcha.textproducer.font.size","40");
		kaptchaProperties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
		//kaptchaProperties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
		kaptchaProperties.put("kaptcha.textproducer.char.string","liqinghui1982");

		Config config = new Config(kaptchaProperties);
		producer =  config.getProducerImpl();
	}

	public ImageValidateCode generate() throws CaptchaException {
		String code = producer.createText();
		//String code = RandomStringUtils.randomAlphanumeric(6);
		BufferedImage image = producer.createImage(code);
		// 60秒过期
		ImageValidateCode imageValidateCode = new ImageValidateCode(image, code, EXPIRE_SECONDS);
		captchaMap.put(code, imageValidateCode);
		return imageValidateCode;
	}

	public void validate(String captchaCode) throws CaptchaException {
		log.debug("#service#captcha#captchaCode:{}", captchaCode);
		if(StringUtils.isBlank(captchaCode)) {
			throw new CaptchaException("验证码不能为空");
		}

		ImageValidateCode imageValidateCode = captchaMap.get(captchaCode);

		if (imageValidateCode == null || StringUtils.isBlank(imageValidateCode.getCode())) {
			throw new CaptchaException("验证码信息"+captchaCode);
		}

		if(imageValidateCode.isExpired()) {
			throw new CaptchaException("验证码已过期");
		}

		if (imageValidateCode.getCode().equals(captchaCode)) {
			captchaMap.remove(captchaCode);
			return;
		}

		throw new CaptchaException("验证码不匹配");
	}

	@Scheduled(cron = "*/1 * * * * ?")
//	@Async 不用等待任务执行完成即可重新执行
	public void check() throws InterruptedException {
		log.debug("checking. size:{}", captchaMap.size());
		Thread.sleep(10000);
		Iterator<Map.Entry<String, ImageValidateCode>> it = captchaMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, ImageValidateCode> entry = it.next();
			ImageValidateCode value = entry.getValue();
			if(needRemove(value)) {
				it.remove();
				log.info("remove:{}", value);
			}
		}
	}

//	@Scheduled(cron = "0 * * * * ?")
//	public void check2() {
//		log.debug("checking2. size:{}", captchaMap.size());
//		Iterator<Map.Entry<String, ImageValidateCode>> it = captchaMap.entrySet().iterator();
//		while(it.hasNext()) {
//			Map.Entry<String, ImageValidateCode> entry = it.next();
//			ImageValidateCode value = entry.getValue();
//			if(isExpired(value)) {
//				it.remove();
//				log.info("remove:{}", value);
//			}
//		}
//	}

	private boolean needRemove(ImageValidateCode imageValidateCode) {
		return LocalDateTime.now().isAfter(imageValidateCode.getExpireTime().plusSeconds(2 * EXPIRE_SECONDS));
	}
}
