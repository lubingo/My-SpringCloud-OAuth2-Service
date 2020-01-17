package com.bingo.auth.core.config.handler;

import com.alibaba.fastjson.JSON;
import com.bingo.auth.core.entity.response.ResponseJsonResult;
import com.bingo.auth.core.entity.response.ResponseResultUtil;
import com.bingo.auth.core.eum.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/13 13:40
 * @since
 */
@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseJsonResult result = ResponseResultUtil.fail(ResultCode.SUCCESS.getCode());
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
