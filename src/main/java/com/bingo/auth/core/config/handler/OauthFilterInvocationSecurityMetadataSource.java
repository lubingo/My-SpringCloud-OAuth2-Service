package com.bingo.auth.core.config.handler;

import com.bingo.auth.core.dao.resource.OauthResourceMapper;
import com.bingo.auth.core.entity.resource.OauthResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/13 17:24
 * @since
 */
@Component
public class OauthFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private AntPathMatcher antPathMatcher = new AntPathMatcher(); // 模糊匹配 如何 auth/**   auth/auth

    @Autowired
    private OauthResourceMapper oauthResourceMapper ;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation )object).getRequestUrl()  ;
        if(requestUrl.contains("?"))
            requestUrl=requestUrl.substring(0,requestUrl.indexOf("?")) ;
        List<OauthResource> list = oauthResourceMapper.selectByResourceUrl(requestUrl) ;


        String[] attributes = new String[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            if(antPathMatcher.match(list.get(i).getResourceUrl(),requestUrl))
                attributes[i] = list.get(i).getRoleName() ;
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
