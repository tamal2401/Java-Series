package com.spring.jpa;

import com.spring.jpa.domain.User;
import com.spring.jpa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class InitializeData {

    private final UserRepo repo;

    @Autowired
    public InitializeData(UserRepo repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initialize(){
        User user1 = new User();
        user1.setUserId(1);
        user1.setEmail("das.tamal00496@gmail.com");
        user1.setFName("Tamal");
        user1.setLName("Das");
        user1.setOrgName("Deloitte");
        user1.setPhnNo("7003192645");

        User user2 = new User();
        user2.setUserId(2);
        user2.setEmail("onlyjohn98.com");
        user2.setFName("John");
        user2.setLName("Paul");
        user2.setOrgName("TCS");
        user2.setPhnNo("8910673912");

        repo.saveAll(Arrays.asList(user1, user2));
    }
}
