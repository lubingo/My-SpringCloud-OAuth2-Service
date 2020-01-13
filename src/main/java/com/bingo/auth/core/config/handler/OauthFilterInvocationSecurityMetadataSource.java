package com.bingo.auth.core.config.handler;

import com.bingo.auth.core.dao.resource.OauthResourceMapper;
import com.bingo.auth.core.entity.resource.OauthResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

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

    @Autowired
    private OauthResourceMapper oauthResourceMapper ;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation )object).getRequestUrl() ;
        List<OauthResource> list = oauthResourceMapper.selectByResourceUrl(requestUrl) ;
        if(list==null || list.size() <1) return null ;
        String[] attributes = new String[list.size()];
        for (int i = 0; i <list.size() ; i++) {
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
        return false;
    }
}
