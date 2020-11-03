package com.demo.dashboard;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @PostMapping(value = "/api/product/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(){

    }
}
