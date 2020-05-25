package com.java.spring.producer.controllers;

import com.java.spring.producer.model.DomainEmployee;
import com.java.spring.producer.model.Employee;
import com.java.spring.producer.service.EmpPersistanceService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MainController {

	@Autowired
	EmpPersistanceService service;

	@GetMapping("/home")
	public String homePage(Model model){
		model.addAttribute("employee", new Employee());
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/create/employee", method = RequestMethod.POST)
	public String firstPage(@RequestBody Employee employee) {
		DomainEmployee dEmp = new DomainEmployee();
		dEmp.setName(employee.getName())
				.setDesignation(employee.getDesignation())
				.setExp(employee.getExp())
				.setRoles(new ArrayList<>())
				.setSalary(employee.getSalary());
		String empId = service.storeData(dEmp);
		return empId;
	}

}
