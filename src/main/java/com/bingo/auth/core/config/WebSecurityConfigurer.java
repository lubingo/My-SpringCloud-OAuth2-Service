package com.bingo.auth.core.config;

import com.bingo.auth.core.service.user.OauthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/12/31 16:13
 * @since
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Resource
    private OauthUserService oauthUserService ;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    @Bean
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(oauthUserService) ;
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("tangTang").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN","USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/static/**","/css/**", "/js/**", "/fonts/**", "/images/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/welcome").hasRole("USER")
                .antMatchers("/oauth").hasRole("USER")
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/welcome")
                .and().logout().permitAll()
        ;
    }

    @Override
    public void configure(WebSecurity web)   {
        //忽略
        web.ignoring().antMatchers("/admin/**");
    }


    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
