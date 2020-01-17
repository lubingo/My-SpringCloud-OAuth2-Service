package com.bingo.auth.core.config.handler;

import com.alibaba.fastjson.JSON;
import com.bingo.auth.core.entity.response.ResponseJsonResult;
import com.bingo.auth.core.entity.response.ResponseResultUtil;
import com.bingo.auth.core.eum.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/13 14:27
 * @since
 */
@Component
public class OauthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseJsonResult result = ResponseResultUtil.fail(ResultCode.NO_PERMISSION.getCode());
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
