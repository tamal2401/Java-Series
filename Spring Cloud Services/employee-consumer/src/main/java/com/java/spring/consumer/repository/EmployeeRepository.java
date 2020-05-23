package com.java.spring.consumer.repository;

import com.java.spring.consumer.controllers.model.DomainEmployee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<DomainEmployee, String> {}
