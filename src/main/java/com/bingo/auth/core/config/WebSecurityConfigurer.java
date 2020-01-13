package com.bingo.auth.core.config;

import com.bingo.auth.core.service.user.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/12/31 16:13
 * @since
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private OauthUserService oauthUserService ;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO 查库 查询用户
        auth.userDetailsService(oauthUserService) ;
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("tangTang").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    @Override
    public void configure(WebSecurity web)   {
        //TODO  个人理解 是忽略权限控制的资源和方法  ， 个人觉得此处 处理静态资源忽略比较合适
        web.ignoring().antMatchers("/login.html" ,"/html/*.html");

    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
