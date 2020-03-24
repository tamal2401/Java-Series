package com.java.covid.controller;

import com.java.covid.model.CovidAllIndiaDataModel;
import com.java.covid.model.CovidStatModel;
import com.java.covid.service.CovidDattaCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    CovidDattaCollectorService dataService;

    @GetMapping(path = "/welcome/india")
    public String welcomePage(Model model) throws IOException {
        CovidAllIndiaDataModel allIndiaStats = dataService.getAllIndianStats();
        model.addAttribute("totalCured", allIndiaStats.getTotalCured());
        model.addAttribute("totalEffected", allIndiaStats.getTotalEffectedCount());
        model.addAttribute("totalDeath", allIndiaStats.getTotalDeath());
        model.addAttribute("totalDataOfIndia", allIndiaStats.getStateData());
        return "indian_data";
    }

    @GetMapping(path = "/welcome/global")
    public String GlobalDataPage(Model model) throws IOException {
        List<CovidStatModel> globalStats = dataService.getGlobaldata();
        long totalCountWorldWide = globalStats.stream().mapToLong(each -> each.getLatestCases()).sum();
        model.addAttribute("allGlobalData", globalStats);
        model.addAttribute("TotalCountWorldWide", totalCountWorldWide);
        return "global_data";
    }

    @GetMapping(path = {"/","/welcome"})
    public String getIndexPage(Model model) throws IOException {
        List<CovidStatModel> globalStats = dataService.getGlobaldata();
        long totalCountWorldWide = globalStats.stream().mapToLong(each -> each.getLatestCases()).sum();
        model.addAttribute("TotalCountWorldWide", totalCountWorldWide);
        return "home";
    }
}
