package com.sidu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by dell on 2017/1/6.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {"com.sidu.controller"}
)
@PropertySource(value = {"classpath:config.properties"}, ignoreResourceNotFound = true)
public class WebConfig extends WebMvcConfigurerAdapter{
    /*视图解析器*/
    @Value("${view.prefix}")
    private String prefix;

    @Value("${view.suffix}")
    private String suffix;

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setPrefix(prefix);
        resourceViewResolver.setSuffix(suffix);
        resourceViewResolver.setExposeContextBeansAsAttributes(true);
        return resourceViewResolver;
    }

    /*静态资源文件 转发给servlet*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*response字符集*/
    @Value("${http.encoding}")
    private String encoding;
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter shm = new StringHttpMessageConverter(Charset.forName(encoding));
        //返回字符集编码设置
        converters.add(shm);
        //@requestbody  json类型转换
        converters.add(new MappingJackson2HttpMessageConverter());
        //xml类型转换
        converters.add(createXmlHttpMessageConverter());
    }

    private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter =
                new MarshallingHttpMessageConverter();

        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);

        return xmlConverter;
    }

    /*cors跨域配置*/
    @Value("${cors.mapping}")
    private String mapping;
    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(mapping)
                .allowedOrigins(allowedOrigins)
                .allowedMethods("PUT", "DELETE","POST","GET")
                .allowCredentials(false).maxAge(3600);
    }
}
