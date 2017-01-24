package com.sidu.service.impl;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by dell on 2017/1/24.
 */
@Service
public class MyJdbcTokenStore extends JdbcTokenStore {
    public MyJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }


    public OAuth2AccessToken readAccessToken(String tokenValue) {
        System.out.println("readAccessToken");
        return super.readAccessToken(tokenValue);
    }


    public void removeAccessToken(String tokenValue) {
        System.out.println("removeAccessToken");
        super.removeAccessToken(tokenValue);
    }


    public OAuth2RefreshToken readRefreshToken(String token) {
        System.out.println("readRefreshToken");
        return super.readRefreshToken(token);
    }

    public void removeRefreshToken(String token) {
        System.out.println("removeRefreshToken");
        super.removeRefreshToken(token);
    }
}
