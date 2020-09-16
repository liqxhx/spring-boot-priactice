package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.config.SecurityConstants;
import com.lqh.priactice.spring.security.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 类描述: PassportController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/08 21:45
 * @since 2020/09/08 21:45
 */
@Controller
public class PassportController {
    public static final String X_REQUEST_WITH = "x-requested-with";
    public static final String JSON_HTTP_REQUEST = "JSONHttpRequest";
    public static final String SIGN_IN_PAGE = "/signin.html";


    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 登录
     * 当需要身份验证时跳转到这里
     */
    @RequestMapping(SecurityConstants.DEFAULT_LOGIN_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseDTO requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            if(StringUtils.hasText(redirectUrl) && !isAjax(request)) {
                redirectStrategy.sendRedirect(request, response, SIGN_IN_PAGE);
            }
        }

        return ResponseDTO.fail("访问问的服务需要身份认证，请引导用户到登录页");
    }

    protected boolean isAjax(HttpServletRequest request) {
        if(request.getRequestURL().toString().endsWith(".html")) {
            return false;
        }
        String xRequestWithHeader = request.getHeader(X_REQUEST_WITH);
        if (org.apache.commons.lang3.StringUtils.isBlank(xRequestWithHeader)) {
            return false;
        }

        String[] array = xRequestWithHeader.split(",");
        for (String str : array) {
            if (JSON_HTTP_REQUEST.equalsIgnoreCase(str.trim())) {
                return true;
            }
        }
        return false;
    }
}
