package com.bingo.auth.core.config.handler;

import com.alibaba.fastjson.JSON;
import com.bingo.auth.core.entity.response.ResponseJsonResult;
import com.bingo.auth.core.entity.response.ResponseResultUtil;
import com.bingo.auth.core.eum.ResultCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/13 16:36
 * @since
 */
@Component
public class OauthSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        ResponseJsonResult result = ResponseResultUtil.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS.getCode());
        HttpServletResponse httpServletResponse = event.getResponse();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
