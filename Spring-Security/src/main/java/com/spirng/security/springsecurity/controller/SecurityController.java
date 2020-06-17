package com.spirng.security.springsecurity.controller;

import com.spirng.security.springsecurity.domain.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/welcome")
    public String welcomePage(Model model, @RequestBody UserModel user){
        model.addAttribute("user", "tamal");
        return "index";
    }
}
