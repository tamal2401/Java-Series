package com.example.schedule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DataService implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("working");
    }
}
