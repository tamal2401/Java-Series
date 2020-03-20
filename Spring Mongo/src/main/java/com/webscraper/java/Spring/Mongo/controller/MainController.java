package com.webscraper.java.Spring.Mongo.controller;

import com.webscraper.java.Spring.Mongo.model.EmployeeModel;
import com.webscraper.java.Spring.Mongo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    CrudService service;

    @GetMapping(value = "/mongo/store")
    public String storeData(){
        List<EmployeeModel> model = Arrays.asList(new EmployeeModel(1, "Tamal","BTA", 680000),new EmployeeModel(2, "Sagnik","BTA", 700000));
        System.out.println(model.toString());
        service.saveObject(model);
        return "stored";
    }
}
