package com.lqh.priactice.springboot.mvc.thymeleaf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * <p> 类描述: I18n
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/17 00:11
 * @since 2020/07/17 00:11
 */
@Component
public class I18n {
    private static MessageSource messageSource;

    @Autowired
    public void setResourceBundle(MessageSource messageSource) {
        I18n.messageSource = messageSource;
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale local) {
        return messageSource.getMessage(code, args, defaultMessage, local);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, Locale.getDefault());
    }
}
