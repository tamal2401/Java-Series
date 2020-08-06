package com.java.stalker.controller;

import com.java.stalker.model.AuthResponse;
import com.java.stalker.model.Post;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostsController {

    @PostMapping("/login")
    public AuthResponse login(){
        AuthResponse auth = new AuthResponse();
        auth.setEmail("");
        return  null;
    }

    @PostMapping("/signup")
    public AuthResponse signup(){
        return  null;
    }

    @PostMapping("/posts")
    public List<Post> getPosts(){
        return  null;
    }
}
