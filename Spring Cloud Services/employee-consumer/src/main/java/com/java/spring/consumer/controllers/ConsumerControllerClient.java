package com.java.spring.consumer.controllers;

import com.java.spring.consumer.controllers.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerControllerClient{

	@Autowired
	RestTemplate template;

	@GetMapping(value="/getemployee", produces = "application/json")
	public Employee getEmployee(){
		Employee emp = template.getForObject("http://employee-producer/employee", Employee.class);
		return emp;
	}
}