package com.java.spring.producer.controllers;

import com.java.spring.producer.model.DomainEmployee;
import com.java.spring.producer.model.Employee;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestController {

	@RequestMapping(value = "/create/employee", method = RequestMethod.POST)
	public Employee firstPage(@RequestBody Employee emp) {
		DomainEmployee dEmp = new DomainEmployee();
		dEmp.setName(emp.getName())
				.setDesignation(emp.getDesignation())
				.setExp(emp.getExp())
				.setRoles(new ArrayList<>())
				.setSalary(emp.getSalary());
		return emp;
	}

}
