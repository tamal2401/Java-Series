package com.javainuse.controllers;

import java.io.IOException;

import com.javainuse.controllers.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
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