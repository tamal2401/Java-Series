package com.demo.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class MainController {
    private RestTemplate template;

    public MainController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/v1/three")
    public String process() throws Exception {
        Thread.sleep(3000);
        int val = ThreadLocalRandom.current().nextInt(1, 100);
        if(val%2!=0){
            throw new Exception("Boom!!!! odd things happened in life");
        }
        return "Service 3";
    }
}
