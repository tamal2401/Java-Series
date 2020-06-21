package com.java.security.springdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/welcome")
    public String welcomePage(){
        return "<h2>Welcome to Home page</h2>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h2>User</h2>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h2>Admin</h2>";
    }
}
