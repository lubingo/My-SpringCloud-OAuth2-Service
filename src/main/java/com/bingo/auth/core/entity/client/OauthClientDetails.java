package com.bingo.auth.core.entity.client;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/7 15:07
 * @since
 */
public class OauthClientDetails implements ClientDetails {



    private String clientId;

    private String clientSecret;

    private Set<String>  scope;

    private Set<String> resourceIds;

    private Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private String autoApproveScopes;

    private  Collection<GrantedAuthority>  authorities;

    private Integer accessTokenValiditySeconds; //set get

    private Integer refreshTokenValiditySeconds; //set get

    private Map<String, Object> additionalInformation;//set get


    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    @Override
    public String getClientId() {
        return this.clientId;
    }


    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }
    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }


    @Override
    public boolean isSecretRequired() {
        return false;
    }


    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }


    @Override
    public boolean isScoped() {
        return false;
    }


    public void setScope(Set<String> scope) {
        this.scope = scope;
    }
    @Override
    public Set<String> getScope() {
        return this.scope;
    }


    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }


    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }


    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds ;
    }


    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds ;
    }


    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }


    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
