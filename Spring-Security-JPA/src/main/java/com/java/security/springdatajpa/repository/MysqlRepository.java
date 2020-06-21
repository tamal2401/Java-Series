package com.java.security.springdatajpa.repository;

import com.java.security.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MysqlRepository extends JpaRepository<User, Integer> {

    Optional<User> findByuserName(String userName);
}
