package com.java.stalker.controller;

import com.java.stalker.model.AuthResponse;
import com.java.stalker.model.Post;
import com.java.stalker.model.User;
import com.java.stalker.model.crud.EnrolledUser;
import com.java.stalker.repo.LoginRepositoryDao;
import com.java.stalker.repo.PostsRepositoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class PostsController {

    private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

    @Autowired
    LoginRepositoryDao loginRepo;

    @Autowired
    PostsRepositoryDao postsRepositoryDao;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {
//        AuthResponse auth = new AuthResponse();
//        auth.setEmail("test@test.com");
//        auth.setToken("12b31h2jb321hk");
//        auth.setUser("Tamal");

        EnrolledUser loggedInUser = null;
        try {
            loggedInUser = loginRepo.findByUserId(user.getUserName());
            loggedInUser.setLastLoggedInTime(new Date());
            loginRepo.save(loggedInUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new AuthResponse(loggedInUser.getUserId(), loggedInUser.getEmail());
    }

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody User signUpUser) throws Exception {
//        AuthResponse auth = new AuthResponse();
//        auth.setEmail("test@test.com");
//        auth.setUser("Tamal");

        EnrolledUser freshUser = new EnrolledUser();

        freshUser = loginRepo.findByUserId(signUpUser.getUserName());
        if (null == freshUser) {
            freshUser.setUserId(signUpUser.getUserName());
            freshUser.setCreatedAt(new Date());
            freshUser.setEmail(signUpUser.getEmail());
            freshUser.setPassword(signUpUser.getPassword());
            freshUser.setLastLoggedInTime(new Date());
        } else {
            throw new Exception("User Already Exists");
        }
        return new AuthResponse(freshUser.getUserId(), freshUser.getEmail());
    }

    @GetMapping("/posts/{userName}")
    public List<Post> getPosts(@PathVariable String userName) {
        Post post1 = new Post();
        post1.setPost("test1");
        post1.setPostTime("11-11-2020 12:40");

        Post post2 = new Post();
        post2.setPost("test2");
        post2.setPostTime("11-11-2020 12:40");

        List<Post> listOfPost = new ArrayList<>();
        listOfPost.add(post1);
        listOfPost.add(post2);
        return listOfPost;
    }

    public static void main(String[] args) {
        List<Integer> space = Arrays.asList(1, 5, 3, 2);
        int segment = 1;

        int localMaxima = 0;
        int itr = (space.size() - (segment - 1)) - 1;
        for (int i = 0; i <= itr; i++) {
            int counter = segment;
            int j = i;
            int localMinima = space.get(j);
            while (counter != 0) {
                if (localMinima > space.get(j)) {
                    localMinima = space.get(j);
                }
                counter--;
                j++;
            }
            if (localMaxima < localMinima) {
                localMaxima = localMinima;
            }
        }
        System.out.println(localMaxima);

    }
}
