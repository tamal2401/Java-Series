package com.spring.security.springsecurity.controller;

import com.spring.security.springsecurity.domain.UserModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class HomeController {

    // acessed for authenticated users with roles any
    @GetMapping("/")
    public String welcomePage(Model model, @RequestBody UserModel user){
        model.addAttribute("user", user.getUserName());
        return "<h2>Welcome to Home page</h2>";
    }

    // acessed for authenticated users with roles USER+ADMIN
    @GetMapping("/user")
    public String user(){
        return "<h2>User</h2>";
    }

    // acessed for authenticated users with roles ADMIN
    @GetMapping("/admin")
    public String admin(){
        return "<h2>Admin</h2>";
    }
}
