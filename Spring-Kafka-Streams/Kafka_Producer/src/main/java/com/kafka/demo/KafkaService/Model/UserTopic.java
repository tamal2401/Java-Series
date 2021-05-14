package com.kafka.demo.KafkaService.Model;

import java.io.Serializable;

public class UserTopic implements Serializable {

    private String employeename;
    private String messsage;

    public UserTopic(String employeename, String messsage) {
        this.employeename = employeename;
        this.messsage = messsage;
    }

    public String getEmployeename() {
        return employeename;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    @Override
    public String toString() {
        return "Employee Name : "+this.employeename+" \n Messege is : "+this.messsage;
    }
}
