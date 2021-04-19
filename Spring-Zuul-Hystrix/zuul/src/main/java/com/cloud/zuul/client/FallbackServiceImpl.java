package com.cloud.zuul.client;

import com.cloud.zuul.model.FallbackModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class FallbackServiceImpl {

    @Autowired
    @LoadBalanced
    RestTemplate template;

    public String call(FallbackModel model) throws JsonProcessingException {
        URI uri = UriComponentsBuilder.newInstance()
                            .scheme("http")
                            .host("Fallback-Service")
                            .path("/fallback/response")
                            .build()
                            .toUri();

        String response = template.postForObject(uri, model, String.class);
        return response;
    }
}
