package com.webscraper.java.Spring.Mongo.service;

import com.webscraper.java.Spring.Mongo.model.EmployeeModel;
import com.webscraper.java.Spring.Mongo.repository.DaomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {

    @Autowired
    private DaomainRepository repo;

    public void saveObject(EmployeeModel model){
        repo.save(model);
    }

    public void saveObject(List<EmployeeModel> models){
        repo.saveAll(models);
    }

    public List<EmployeeModel> find(){
        return repo.findAll();
    }
}
