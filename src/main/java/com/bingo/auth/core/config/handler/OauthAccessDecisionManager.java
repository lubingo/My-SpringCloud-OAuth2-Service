package com.bingo.auth.core.config.handler;

import org.bouncycastle.util.Arrays;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/13 16:45
 * @since
 */
@Component
public class OauthAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator() ;
        while (iterator.hasNext()){
            ConfigAttribute configAttribute = iterator.next() ;
            // permissions required for the current request
            String needRole = new StringBuilder("ROLE_").append(configAttribute.getAttribute()).toString() ;
            // the permissions of the current user
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority ga : authorities){
                if(ga.getAuthority().equals(needRole))
                    return;
            }
        }
        throw  new AccessDeniedException("permission denied ");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
