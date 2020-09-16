package com.lqh.priactice.spring.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqh.priactice.spring.security.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 类描述: MyAuthenticationSuccessHandler
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/09/07 20:46
 * @since 2020/09/07 20:46
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String value = objectMapper.writeValueAsString(ResponseDTO.success("登录成功", authentication));
        log.info("登录成功：{}", value);

        if(shouldResponseJson(request)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE.concat(";charset=UTF-8"));
            response.getWriter().write(value);
            super.clearAuthenticationAttributes(request);
        } else {
//            requestCache.removeRequest(request, response);
//            setAlwaysUseDefaultTargetUrl(false);
//            super.onAuthenticationSuccess(request, response, authentication);

            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if(savedRequest != null) {
                String redirectUrl = savedRequest.getRedirectUrl();
                if(org.springframework.util.StringUtils.hasText(redirectUrl)) {
                    redirectStrategy.sendRedirect(request, response, redirectUrl);
                }
            }
        }

    }

    private boolean shouldResponseJson(HttpServletRequest request) {
        return StringUtils.endsWithAny(request.getRequestURL().toString().toLowerCase(), ".json", ".ajax");
    }
}
