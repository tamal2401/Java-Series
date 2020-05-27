package com.java.covid.controller;

import com.java.covid.model.india.CovidAllIndiaDataModel;
import com.java.covid.model.CovidStatModel;
import com.java.covid.model.india.EachDayCountMapping;
import com.java.covid.model.timeseries.TimeSeriesDataModel;
import com.java.covid.service.CovidDattaCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DataController {

    @Autowired
    CovidDattaCollectorService dataService;

    @GetMapping(path = "/welcome/mapping")
    public String visualizePage(Model model) throws IOException {

        List<TimeSeriesDataModel> plotData = dataService.seriesModel;
        /*List<EachDayCountMapping> activeIncidentList = plotData.
                stream()
                .map(each -> new EachDayCountMapping(each.getTotalconfirmed(), each.getDate()))
                .collect(Collectors.toList());*/
        List<Integer> activeIncidentList = plotData.stream().map(each -> each.getTotalconfirmed()).collect(Collectors.toList());
        List<String> dailyDate = plotData.stream().map(each -> each.getDate()).collect(Collectors.toList());
        List<Integer> recoveredList = plotData.stream().map(each -> each.getTotalrecovered()).collect(Collectors.toList());

        model.addAttribute("count", activeIncidentList);
        model.addAttribute("date", dailyDate);
        model.addAttribute("recovered", recoveredList);
        return "visualize_data";
    }

    @GetMapping(path = "/welcome/india")
    public String welcomePage(Model model) throws IOException {
        CovidAllIndiaDataModel allIndiaStats = dataService.getAllIndianStats();

        model.addAttribute("totalCured", allIndiaStats.getTotalRecovered());
        model.addAttribute("totalConfirmed", allIndiaStats.getTotalConfirmed());
        model.addAttribute("totalDeath", allIndiaStats.getTotalDeath());
        model.addAttribute("totalDataOfIndia", allIndiaStats.getStateData());
        model.addAttribute("lastUpdated", allIndiaStats.getLastUpdated());
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
