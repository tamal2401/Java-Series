package com.java.jobscraper.controller;

import com.java.jobscraper.pojo.JobDetailsRequest;
import com.java.jobscraper.pojo.JobDetailsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @GetMapping(path="/welcome")
    public String applicationPage(Model model){
        model.addAttribute("jobDetailsRequest", new JobDetailsRequest());
        return "index";
    }

    @PostMapping(path = "/naukhri/jobs}", produces = "application/json")
    public List<JobDetailsResponse> getJobDetails(@ModelAttribute JobDetailsRequest jobDetailsRequest){
        return null;
    }
}
