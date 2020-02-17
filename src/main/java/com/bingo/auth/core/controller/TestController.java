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
import javax.swing.text.html.Option;
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

    @RequestMapping(value = "/v1" ,method = {RequestMethod.POST,RequestMethod.GET  } ,produces = "application/json")
    public ResponseJsonResult v1(String  str){

//        title: {
//            text: '天津银行理财经理销售量'
//        },
//        tooltip: {},
//        legend: {
//            data: ["理财经理销量"]
//        },
//        xAxis: {
//            data: ["张三","李四","王五","李柳","田七","周八"]
//        },
//        yAxis: {},
//        series: [{
//            name: '销量',
//                    type: 'bar',
//                    data: [5, 20, 366, 100, 10, 20]
//        }]


        return ResponseResultUtil.success(str)  ;
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
