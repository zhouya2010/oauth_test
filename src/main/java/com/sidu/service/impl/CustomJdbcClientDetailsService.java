package com.sidu.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


/**
 * 扩展 默认的 ClientDetailsService, 增加逻辑删除判断( archived = 0)
 * SQL中添加  <i>archived = 0</i> 条件
 * <p/>
 * 增加缓存支持
 *
 * @author Shengzhao Li
 */
@Service
@Primary
public class CustomJdbcClientDetailsService extends JdbcClientDetailsService {

    private static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            "web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            "from oauth_client_details where client_id = ? and archived = 0 ";


    public CustomJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
        setSelectClientDetailsSql(SELECT_CLIENT_DETAILS_SQL);
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        System.out.println("loadClientByClientId");
        return super.loadClientByClientId(clientId);
    }


    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        System.out.println("updateClientDetails");

        super.updateClientDetails(clientDetails);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        System.out.println("updateClientSecret");

        super.updateClientSecret(clientId, secret);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        System.out.println("removeClientDetails");

        super.removeClientDetails(clientId);
    }
}