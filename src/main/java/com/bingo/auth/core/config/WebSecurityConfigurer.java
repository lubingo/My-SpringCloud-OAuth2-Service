package com.bingo.auth.core.config;

import com.bingo.auth.core.service.user.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
      super.configure(http);
    }

    @Override
    public void configure(WebSecurity web)   {

        //TODO  个人理解 是忽略权限控制的资源和方法
        //通过查询数据库， 拉取需要被排除的数据

        web.ignoring().antMatchers("/admin/**","/html/**");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
