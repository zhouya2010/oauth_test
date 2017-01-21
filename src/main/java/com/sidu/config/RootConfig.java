package com.sidu.config;


import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by dell on 2017/1/6.
 */
@Configuration
@Import(DataSourceConfig.class)
@ComponentScan(
        basePackages = {"com.sidu.dao", "com.sidu.domain", "com.sidu.service"},
        basePackageClasses = {SecurityConfiguration.class},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)}
)
public class RootConfig {

}
