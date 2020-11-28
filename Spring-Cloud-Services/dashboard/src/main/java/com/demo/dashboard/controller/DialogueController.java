package com.demo.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DialogueController {

    @GetMapping(value = "/get/insult")
    public String getNewInsult(){
        return "index";
    }

    @GetMapping(value = "/get/quote")
    public String getNewQuote(){
        return "index";
    }
}
