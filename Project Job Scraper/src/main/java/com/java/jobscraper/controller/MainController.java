package com.java.jobscraper.controller;

import com.java.jobscraper.pojo.JobDetailsRequest;
import com.java.jobscraper.pojo.JobDetailsResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @CrossOrigin
    @PostMapping(path = "/naukhri/jobs}", produces = "application/json")
    public List<JobDetailsResponse> getJobDetails(@RequestBody JobDetailsRequest request){
        return null;
    }
}
