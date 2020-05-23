package com.java.spring.producer.model;

import javax.annotation.Generated;
import java.util.List;

public class DomainEmployee {

	private String empId;
	private String name;
	private String designation;
	private List<String> roles;
	private double salary;
	private int exp;

	public String getEmpId() {
		return empId;
	}

	public DomainEmployee setEmpId(String empId) {
		this.empId = empId;
		return this;
	}

	public String getName() {
		return name;
	}

	public DomainEmployee setName(String name) {
		this.name = name;
		return this;
	}

	public String getDesignation() {
		return designation;
	}

	public DomainEmployee setDesignation(String designation) {
		this.designation = designation;
		return this;
	}

	public List<String> getRoles() {
		return roles;
	}

	public DomainEmployee setRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}

	public double getSalary() {
		return salary;
	}

	public DomainEmployee setSalary(double salary) {
		this.salary = salary;
		return this;
	}

	public int getExp() {
		return exp;
	}

	public DomainEmployee setExp(int exp) {
		this.exp = exp;
		return this;
	}
}
