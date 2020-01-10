package com.bingo.auth.core.config;

import com.bingo.auth.core.dao.user.OauthUserMapper;
import com.bingo.auth.core.entity.user.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

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
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(serverResource);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //TODO http 相关配置，包括登入 登出、异常处理、回话处理等 逻辑

        //查询数据表， 拉取对应数据  资源 和对应  角色
        OauthUser user = oauthUserMapper.selectByUserName("tangTang1");

        http.csrf().disable().authorizeRequests()
                .antMatchers("/v1").hasRole("ADMIN").antMatchers("/v1").hasRole("USER").antMatchers("/html/**","/login.html").hasAnyRole("ADMIN")
                .anyRequest().authenticated();
    }


}
