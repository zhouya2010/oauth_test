package com.sidu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sidu.service.UserService;
import com.sidu.service.impl.CustomJdbcClientDetailsService;
import com.sidu.service.impl.MyJdbcTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;


/**
 * Created by dell on 2017/1/21.
 */
@Configuration
@EnableWebSecurity
@Import(DataSourceConfig.class)
@Order(2)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("AuthenticationManagerBuilder Configure");
        auth.userDetailsService(userService)
                .passwordEncoder(new Md5PasswordEncoder());

    }

    @Override
    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.expressionHandler(new OAuth2WebSecurityExpressionHandler());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**").hasAnyRole("ROLE_USER", "ROLE_UNITY", "ROLE_MOBILE")
                .antMatchers("/user/**").hasAnyRole("ROLE_USER")
//                .and()
//                .exceptionHandling().accessDeniedPage("/login.jsp?authorization_error=2")
                .and()
                .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
                .logout()
//                    .logoutUrl("/logout.do")
//                    .logoutSuccessUrl("/index.jsp")
                .and()
                    .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll();
//                    .loginProcessingUrl("/login.do")
//                    .failureUrl("/login.jsp?authentication_error=1")
//                    .loginPage("/login.jsp")
//                    .defaultSuccessUrl("/index.jsp");
    }


      /*
    * OAuth2 Configuration start
    * */

    @Bean(name = "clientDetailsService")
    public ClientDetailsService clientDetailsService(DruidDataSource dataSource) {
        System.out.println("clientDetailsService");
        return new CustomJdbcClientDetailsService(dataSource);
    }

    @Bean(name = "tokenStore")
    public TokenStore tokenStore(DruidDataSource dataSource) {
        System.out.println("tokenStore");
        return new MyJdbcTokenStore(dataSource);
    }

    @Bean(name = "oAuth2RequestFactory")
    public OAuth2RequestFactory oAuth2RequestFactory(ClientDetailsService clientDetailsService) {
        System.out.println("oAuth2RequestFactory");
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    @Bean(name = "oauthUserApprovalHandler")
    public UserApprovalHandler oauthUserApprovalHandler(TokenStore tokenStore, ClientDetailsService clientDetailsService, OAuth2RequestFactory oAuth2RequestFactory) {
        System.out.println("oauthUserApprovalHandler");
        TokenStoreUserApprovalHandler userApprovalHandler = new TokenStoreUserApprovalHandler();
        userApprovalHandler.setTokenStore(tokenStore);
        userApprovalHandler.setClientDetailsService(clientDetailsService);
        userApprovalHandler.setRequestFactory(oAuth2RequestFactory);
//        userApprovalHandler.setOauthService(oauthService);
        return userApprovalHandler;
    }

    @Bean(name = "jdbcAuthorizationCodeServices")
    public AuthorizationCodeServices jdbcAuthorizationCodeServices(DruidDataSource dataSource) {
        System.out.println("jdbcAuthorizationCodeServices");
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean(name = "oauth2AuthenticationEntryPoint")
    public OAuth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {
        System.out.println("oauth2AuthenticationEntryPoint");
        return new OAuth2AuthenticationEntryPoint();
    }

    @Autowired
    @Bean(name = "oauth2ClientDetailsUserService")
    public ClientDetailsUserDetailsService oauth2ClientDetailsUserService(ClientDetailsService clientDetailsService) {
        System.out.println("oauth2ClientDetailsUserService");
        return new ClientDetailsUserDetailsService(clientDetailsService);
    }

    @Bean(name = "oauth2AccessDecisionManager")
    public UnanimousBased oauth2AccessDecisionManager() {
        System.out.println("oauth2AccessDecisionManager");
        return new UnanimousBased(Arrays.<AccessDecisionVoter<? extends Object>>asList(new ScopeVoter(), new RoleVoter(), new AuthenticatedVoter()));
    }

    @Bean(name = "oauth2AccessDeniedHandler")
    public OAuth2AccessDeniedHandler oauth2AccessDeniedHandler() {
        System.out.println("oauth2AccessDeniedHandler");
        return new OAuth2AccessDeniedHandler();
    }

}
