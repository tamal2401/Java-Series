package com.java.spring.producer.model;

public class Employee {
	// private String empId;
	private String name;
	private String designation;
	private double salary;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
