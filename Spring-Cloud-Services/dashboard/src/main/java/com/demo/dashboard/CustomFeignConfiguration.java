package com.demo.dashboard;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class CustomFeignConfiguration {
    @Bean
    @LoadBalanced
    public RestTemplate getTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
