package com.bingo.auth.core.controller;

import com.bingo.auth.core.entity.response.ResponseJsonResult;
import com.bingo.auth.core.entity.response.ResponseResultUtil;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
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
    public ResponseJsonResult  user(HttpServletRequest request , OAuth2Authentication user){
        Map<String,Object> userInfo = new HashMap() ;
        userInfo.put("user" , user.getUserAuthentication());
        userInfo.put("authorities" , AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        request.setAttribute("user",userInfo);
        return ResponseResultUtil.success(userInfo)  ;
    }

    @RequestMapping(value = "/v1" ,method = {RequestMethod.POST,RequestMethod.GET} ,produces = "application/json")
    public String v1(){

        return "method v1 is arrive" ;
    }

    @RequestMapping(value = "/v12" ,method = {RequestMethod.POST,RequestMethod.GET} ,produces = "application/json")
    public String v12(){

        return "method v12 is arrive" ;
    }

    @RequestMapping(value = "/admin" ,method = {RequestMethod.POST,RequestMethod.GET} ,produces = "application/json")
    public String admin(){

        return "method admin is arrive" ;
    }
    @RequestMapping(value = "/login" ,method = {RequestMethod.POST,RequestMethod.GET} ,produces = "application/json")
    public String login(HttpServletRequest request , HttpServletResponse response ,String username , String password){

        return "method login is arrive" ;
    }
    @RequestMapping(value = "/welcome" ,method = {RequestMethod.POST,RequestMethod.GET} ,produces = "application/json")
    public String welcome(){

        return "method login is arrive" ;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}
