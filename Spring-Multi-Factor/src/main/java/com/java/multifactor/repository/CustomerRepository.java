package com.java.multifactor.repository;

import com.java.multifactor.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT u FROM Customer WHERE u.email = ?1", nativeQuery = true)
    Customer findCustomerByEmail(String email);
}
