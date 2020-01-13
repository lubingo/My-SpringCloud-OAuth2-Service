package com.bingo.auth.core.config;

import com.bingo.auth.core.config.handler.*;
import com.bingo.auth.core.dao.user.OauthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/8 9:07
 * @since
 */
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${spring.application.name}")
    private  String  serverResource ;
    @Autowired
    private OauthUserMapper oauthUserMapper ;

    @Autowired
    private  OauthAuthenticationSuccessHandler oauthAuthenticationSuccessHandler ;

    @Autowired
    private OauthAuthenticationFailureHandler oauthAuthenticationFailureHandler ;
    @Autowired
    private OauthAccessDeniedHandler oauthAccessDeniedHandler ;
    @Autowired
    private OauthAuthenticationEntryPoint oauthAuthenticationEntryPoint ;

    @Autowired
    private OauthSessionInformationExpiredStrategy oauthSessionInformationExpiredStrategy ;

    @Autowired
    private  OauthLogoutSuccessHandler oauthLogoutSuccessHandler ;


    @Autowired
    private OauthAccessDecisionManager oauthAccessDecisionManager ;

    @Autowired
    private OauthFilterInvocationSecurityMetadataSource oauthFilterInvocationSecurityMetadataSource ;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(serverResource);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //TODO http 相关配置，包括登入 登出、异常处理、回话处理等 逻辑
        http.csrf().disable().authorizeRequests().anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(oauthAccessDecisionManager);
                        object.setSecurityMetadataSource(oauthFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                //登入
                .and().formLogin()
                    //允许所有用户
                    .permitAll().loginPage("/login.html")
                    //成功处理逻辑
                    .successHandler(oauthAuthenticationSuccessHandler)
                    //失败逻辑
                    .failureHandler(oauthAuthenticationFailureHandler)
                //登出
                .and().logout()
                    //允许所有用户
                    .permitAll().logoutUrl("/logout")
                    .logoutSuccessHandler(oauthLogoutSuccessHandler)//登出成功处理逻辑
                    .deleteCookies("JSESSIONID")//登出之后删除cookie
                //异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                    //权限拒绝处理逻辑
                    .accessDeniedHandler(oauthAccessDeniedHandler)
                    //匿名用户访问无权限资源时的异常处理 未登陆
                    .authenticationEntryPoint(oauthAuthenticationEntryPoint)
                //会话管理
                .and().sessionManagement()
                    //同一账号同时登录最大用户数
                    .maximumSessions(1)
                    //会话失效(账号被挤下线)处理逻辑
                    .expiredSessionStrategy(oauthSessionInformationExpiredStrategy);

    }

}
