package com.java.spring.consumer.services;

import com.java.spring.consumer.controllers.model.DomainEmployee;
import com.java.spring.consumer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistanceServiceImpl implements  PersistanceServices{

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public DomainEmployee saveEmployee(DomainEmployee emp) {
        empRepo.save(emp);
        return emp;
    }
}
