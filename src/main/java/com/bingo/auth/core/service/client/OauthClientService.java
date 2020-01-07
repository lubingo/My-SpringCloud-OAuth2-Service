package com.bingo.auth.core.service.client;

import com.bingo.auth.core.dao.client.OauthClientMapper;
import com.bingo.auth.core.entity.client.OauthClient;
import com.bingo.auth.core.entity.client.OauthClientDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/7 14:03
 * @since
 */
@Service
public class OauthClientService implements ClientDetailsService {

    private Logger logger = LoggerFactory.getLogger(OauthClientService.class);

    @Autowired
    private OauthClientMapper oauthClientMapper ;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        OauthClient oauthClient = oauthClientMapper.selectByClientId(clientId);
        if(){

        }




        return null;
    }
}
