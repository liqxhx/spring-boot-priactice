package com.lqh.priactice.spring.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqh.priactice.spring.security.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 类描述: MyAuthenticationFailureHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/07 20:51
 * @since 2020/09/07 20:51
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String value = objectMapper.writeValueAsString(ResponseDTO.fail("登录失败", exception.getMessage()));
        log.warn("登录失败：{}", value);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE.concat(";charset=UTF-8"));
        response.getWriter().write(value);
    }
}
