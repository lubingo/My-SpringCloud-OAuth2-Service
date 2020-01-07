package com.bingo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthorizationServer
@EnableResourceServer
public class MySpringCloudOAuth2ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringCloudOAuth2ServiceApplication.class, args);
    }

}
