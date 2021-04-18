package com.cloud.zuul.client;

import com.cloud.zuul.model.FallbackModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class FallbackServiceImpl {

    @Autowired
    @LoadBalanced
    RestTemplate template;

    @Autowired
    ObjectMapper mapper;

    public String call(FallbackModel model) throws JsonProcessingException {
        URI uri = UriComponentsBuilder.newInstance()
                            .scheme("http")
                            .host("Fallback-Service")
                            .path("/fallback/response")
                            .build()
                            .toUri();

        Object response = template.postForEntity(uri, RequestMethod.POST.name(), Object.class);
        return mapper.writeValueAsString(response);
    }
}
