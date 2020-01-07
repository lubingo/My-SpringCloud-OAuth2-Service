package com.bingo.auth.core.service.user;

import com.bingo.auth.core.dao.user.OauthUserMapper;
import com.bingo.auth.core.entity.user.OauthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/6 13:31
 * @since
 */
@Service
public class OauthUserService<T extends OauthUser>  implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(OauthUserService.class);
    @Autowired
    private OauthUserMapper oauthUserMapper ;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        try {
            OauthUser  user = oauthUserMapper.selectByUserName(s);
            if(user == null) throw  new UsernameNotFoundException("The user does not exist !");

            List<SimpleGrantedAuthority> authorities = new ArrayList<>() ;
            if(!StringUtils.isEmpty(user.getRoles())){
                String[] roles = user.getRoles().split(",") ;
                for (String role : roles){
                    authorities.add(new SimpleGrantedAuthority(role.trim()));
                }
            }
            return  new User(user.getUserName(),user.getPassword(),authorities);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            logger.error("get user error :{}",e);
            return null ;
        }


    }
}
