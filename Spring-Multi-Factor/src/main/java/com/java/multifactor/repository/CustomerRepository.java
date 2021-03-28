package com.java.multifactor.repository;

import com.java.multifactor.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE email = ?1", nativeQuery = true)
    Customer findCustomerByEmail(String email);
}
