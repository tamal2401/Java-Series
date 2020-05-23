package com.java.spring.consumer.services;

import com.java.spring.consumer.controllers.model.DomainEmployee;

public interface PersistanceServices {

    DomainEmployee saveEmployee(DomainEmployee emp);
}
