package com.demo.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class MainController {
    private RestTemplate template;

    public MainController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/v1/two")
    public String process() throws Exception {
        String response = this.template.getForObject("http://localhost:8084/v1/three", String.class);
        int val = ThreadLocalRandom.current().nextInt(1, 100);
        if (val % 2 != 0) {
            throw new Exception("Boom!!!! odd things happened in life");
        }
        Thread.sleep(3000);
        return "Service 2 <- " + response;
    }
}
