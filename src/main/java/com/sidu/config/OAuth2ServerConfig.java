package com.sidu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by dell on 2017/1/22.
 */
@Configuration
public class OAuth2ServerConfig {
    private static final String UNITY_RESOURCE_ID = "unity-resource";
    private static final String MOBILE_RESOURCE_ID = "mobile-resource";

    //  unity-resource
    @Configuration
    @EnableResourceServer
    protected static class UnityResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Autowired
        private AccessDecisionManager oauth2AccessDecisionManager;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(UNITY_RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().antMatchers("/unity/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/unity/**")
                    .access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_UNITY'))");
//                    .accessDecisionManager(oauth2AccessDecisionManager)
//                    .and().csrf().disable();

        }
    }

    //AuthorizationServer
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;

        @Autowired
        private ClientDetailsService clientDetailsService;

        @Autowired
        private UserApprovalHandler userApprovalHandler;

        @Autowired
        private AuthorizationCodeServices authorizationCodeServices;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService);
//            clients.inMemory().withClient("unity-client")
//                    .resourceIds(UNITY_RESOURCE_ID)
//                    .authorizedGrantTypes("authorization_code", "refresh_token", "implicit")
//                    .authorities("ROLE_UNITY")
//                    .scopes("read")
//                    .secret("unity")
//                    .and()
//                    .withClient("mobile-client")
//                    .resourceIds(MOBILE_RESOURCE_ID)
//                    .authorizedGrantTypes("password", "refresh_token","authorization_code", "refresh_token", "implicit")
//                    .authorities("ROLE_CLIENT")
//                    .scopes("read")
//                    .secret("mobile");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

            System.out.println("AuthorizationServerEndpointsConfigurer");

            endpoints.tokenStore(tokenStore)
                    .userApprovalHandler(userApprovalHandler)
                    .authorizationCodeServices(authorizationCodeServices);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            security.accessDeniedHandler(oauth2AccessDeniedHandler)
//                    .authenticationEntryPoint(oAuth2AuthenticationEntryPoint)
//                    .allowFormAuthenticationForClients();
            security.realm("spring-oauth-server_realm");
        }
    }
}
