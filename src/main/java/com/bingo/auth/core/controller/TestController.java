package com.bingo.auth.core.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/12/31 15:39
 * @since
 */
@RestController
public class TestController {

    @RequestMapping(value = "/user" ,method = {RequestMethod.POST,RequestMethod.GET} ,produces = "application/json")
    public Map<String,Object> user(OAuth2Authentication user){
        Map<String,Object> userInfo = new HashMap() ;
        userInfo.put("user" , user.getUserAuthentication());
        userInfo.put("authorities" , AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return  userInfo ;
    }
    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}
