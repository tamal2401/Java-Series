package com.demo.dashboard.dialogueservice;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class DsServiceConfiguration implements InitializingBean {

    @Bean
    @ConfigurationProperties(prefix = "external.service")
    public ServiceProperties loadProperties() {
        return new ServiceProperties();
    }

    @Bean
    public OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingRequestInterceptor())
                .build();
    }

    @Override
    public void afterPropertiesSet() {
        ServiceProperties properties = loadProperties();
        System.out.println(properties.toString());
    }
}
