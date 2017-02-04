package com.sidu.config;

import com.sidu.credentials.RetryLimitHashedCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * Created by dell on 2017/2/4.
 */
public class ShiroConfig {

    @Bean
    EhCacheManager cacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return manager;
    }

    @Bean
    CredentialsMatcher credentialsMatcher(){
        RetryLimitHashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher(cacheManager());
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        matcher.setStoredCredentialsHexEncoded(true);
        return  matcher;
    }

}
