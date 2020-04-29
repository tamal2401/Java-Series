package com.webscraper.java.Spring.Mongo.repository;

import com.webscraper.java.Spring.Mongo.model.EmployeeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DaomainRepository extends MongoRepository<EmployeeModel, Integer>{
}
