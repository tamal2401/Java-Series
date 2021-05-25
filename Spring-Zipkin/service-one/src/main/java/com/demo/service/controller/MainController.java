package com.demo.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
    private RestTemplate template;

    public MainController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/v1/one")
    public String process() throws InterruptedException {
        String response = this.template.getForObject("http://localhost:8083/v1/two", String.class);
        Thread.sleep(3000);
        return "Service 1 <- "+response;
    }
}
