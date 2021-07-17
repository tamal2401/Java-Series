package com.example.schedule.controller;

import com.example.schedule.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    DataService dataService;
    @GetMapping("/get")
    public void get(){
    }
}
