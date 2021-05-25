package com.demo.zipkin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    private RestTemplate template;

    public MainController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/v1/zipkin")
    public String process() throws InterruptedException {
        String response = this.template.getForObject("http://localhost:8082/v1/one", String.class);
        Thread.sleep(3000);
        return "returning from zipkin <- "+response;
    }
}
