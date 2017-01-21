package com.sidu.config;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/**
 * Created by dell on 2017/1/11.
 */
@Configuration
@PropertySource({"classpath:config.properties"})
public class QiniuConfig {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    @Value("${qiniu.ACCESS_KEY}")
    public  String ACCESS_KEY ;
    @Value("${qiniu.SECRET_KEY}")
    public  String SECRET_KEY ;
    //要上传的空间
    @Value("${qiniu.bucketname}")
    public  String bucketname;

    @Bean
    @Scope(value = "singleton")
    public Auth auth(){
        return Auth.create(ACCESS_KEY, SECRET_KEY);
    }

}
