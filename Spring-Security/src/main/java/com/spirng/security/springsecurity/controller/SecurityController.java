package com.spirng.security.springsecurity.controller;

import com.spirng.security.springsecurity.domain.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {

    @GetMapping("/welcome")
    public String welcomePage(Model model, @RequestBody UserModel user){
        model.addAttribute("user", user.getUserName());
        return "index";
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
