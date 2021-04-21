package com.cloud.zuul.client;

import com.cloud.zuul.model.FallbackModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.invoke.MethodHandles;
import java.net.URI;

@Component
public class FallbackServiceImpl implements FallbackService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Autowired
    @LoadBalanced
    RestTemplate template;

    @HystrixCommand(fallbackMethod = "getHystrixResponse")
    @Override
    public String call(FallbackModel model) {
        URI uri = UriComponentsBuilder.newInstance()
                            .scheme("http")
                            .host("Fallback-Service")
                            .path("/fallback/response")
                            .build()
                            .toUri();

        String response = template.postForObject(uri, model, String.class);
        return response;
    }

    public String getHystrixResponse(FallbackModel model){
        log.error("Fallback service call failed with request object: {}", model.toString());
        return "Fallback service is down. Need IMMEDIATE attention!!!";
    }
}
