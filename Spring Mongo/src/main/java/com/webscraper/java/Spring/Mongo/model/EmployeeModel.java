package com.webscraper.java.Spring.Mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "employee")
public class EmployeeModel {

    @Id
    private int id;
    private String name;
    private Object roles;
    private int salary;

    public EmployeeModel(int id, String name, Object roles, int salary) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Object getRoles() {
        return roles;
    }

    public int getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                ", salary=" + salary +
                '}';
    }
}
