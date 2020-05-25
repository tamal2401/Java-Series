package com.java.spring.producer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "employee.management")
public class EmployeeConfiguration {

    List<String> roles;
    HashMap<String, List<String>> designations;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public HashMap<String, List<String>> getDesignations() {
        return designations;
    }

    public void setDesignations(HashMap<String, List<String>> designations) {
        this.designations = designations;
    }

    @PostConstruct
    public void afterInitialization() throws Exception {
        if(roles.isEmpty() || designations.isEmpty()){
            throw new Exception("Configuratios are empty or not loaded from config server");
        }
    }
}
