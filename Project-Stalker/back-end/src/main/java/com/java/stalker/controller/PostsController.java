package com.java.stalker.controller;

import com.java.stalker.model.AuthResponse;
import com.java.stalker.model.Post;
import com.java.stalker.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PostsController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {
        AuthResponse auth = new AuthResponse();
        auth.setEmail("test@test.com");
        auth.setToken("12b31h2jb321hk");
        auth.setUser("Tamal");
        return auth;
    }

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody User signUpUser) {
        AuthResponse auth = new AuthResponse();
        auth.setEmail("test@test.com");
        auth.setToken("12b31h2jb321hk");
        auth.setUser("Tamal");
        return auth;
    }

    @GetMapping("/posts/{userName}")
    public List<Post> getPosts(@PathVariable String userName) {
        Post post1=new Post();
        post1.setPost("test1");
        post1.setPostTime("11-11-2020 12:40");

        Post post2=new Post();
        post2.setPost("test2");
        post2.setPostTime("11-11-2020 12:40");

        List<Post> listOfPost = new ArrayList<>();
        listOfPost.add(post1);
        listOfPost.add(post2);
        return listOfPost;
    }
}
