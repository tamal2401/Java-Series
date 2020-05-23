package com.java.spring.consumer.controllers;

		import com.java.spring.consumer.controllers.model.DomainEmployee;
		import com.java.spring.consumer.services.PersistanceServices;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerControllerClient{

	@Autowired
	PersistanceServices service;

	@RequestMapping(method = RequestMethod.POST, value = "/consumer/newemployee")
	String persistEmployee(@RequestBody DomainEmployee dEmp){
		String empId = service.saveEmployee(dEmp).getEmpId();
		return empId;
	}
}