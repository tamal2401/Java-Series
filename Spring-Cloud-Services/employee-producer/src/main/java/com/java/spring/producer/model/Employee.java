package com.java.spring.producer.model;

import com.sun.istack.internal.NotNull;

public class Employee {

	private String name;
	private String designation;
	private int salary;
	private int exp;

	public Employee() {
	}

	public String getName() {
		return name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
