package com.java.spring.producer.service;

import com.java.spring.producer.model.DomainEmployee;
import com.java.spring.producer.service.feign.EmployeeConsumerFeignClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpPersistanceService {

    @Autowired
    EmployeeConsumerFeignClient feignController;

    public String storeData(DomainEmployee emp){
        String empId = feignController.persistEmployee(emp);
        return empId;
    }
}
