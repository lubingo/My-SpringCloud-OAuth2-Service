package com.bingo.auth.core.service.client;

import com.bingo.auth.core.dao.client.OauthClientMapper;
import com.bingo.auth.core.entity.client.OauthClient;
import com.bingo.auth.core.entity.client.OauthClientDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.*;

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

        try {
            OauthClient oauthClient = oauthClientMapper.selectByClientId(clientId);
            if(oauthClient == null) throw  new ClientRegistrationException("The Client does not exist !");
            OauthClientDetails oauthClientDetails = new OauthClientDetails() ;
            oauthClientDetails.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValiditySeconds());
            oauthClientDetails.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValiditySeconds());
            oauthClientDetails.setClientSecret(oauthClient.getClientSecret());
            oauthClientDetails.setClientId(oauthClient.getClientId());

            List<String> list1 = Arrays.asList(oauthClient.getScope().trim().split(","));
            Set<String> set1 = new HashSet<>(list1);
            oauthClientDetails.setScope(set1);

            oauthClientDetails.setAdditionalInformation(null);

            List<String> list_AuthorizedGrantTypes = Arrays.asList(oauthClient.getAuthorizedGrantTypes().trim().split(","));
            Set<String> set_AuthorizedGrantTypes = new HashSet<>(list_AuthorizedGrantTypes);
            oauthClientDetails.setAuthorizedGrantTypes(set_AuthorizedGrantTypes);

            String[] strRole = oauthClient.getAuthorities().trim().split(",") ;
            List l_Authorities = new ArrayList<GrantedAuthority>() ;
            for (int i = 0; i <strRole.length ; i++) {
                if(!"".equals(strRole[i]))
                    l_Authorities.add( new SimpleGrantedAuthority(strRole[i])) ;
            }
            oauthClientDetails.setAuthorities(l_Authorities);

            List<String> list_ResourceIds = Arrays.asList(oauthClient.getResourceIds().trim().split(","));
            Set<String> set_ResourceIds = new HashSet<>(list_ResourceIds);
            oauthClientDetails.setResourceIds(set_ResourceIds);

            List<String> list_RegisteredRedirectUris = Arrays.asList(oauthClient.getRegisteredRedirectUris().trim().split(","));
            Set<String> set_RegisteredRedirectUris = new HashSet<>(list_RegisteredRedirectUris);
            oauthClientDetails.setRegisteredRedirectUri(set_RegisteredRedirectUris);
            return oauthClientDetails;
        } catch (ClientRegistrationException e) {
            //e.printStackTrace();
            logger.error("get client error",e);
            return null;
        }
    }
}
