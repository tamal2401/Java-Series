package com.spring.jpa.repository;

import com.spring.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    //@Query(value = "SELECT * FROM USER u WHERE u.email_id= ?1", nativeQuery = true)
    User findUserByEmail(String email);
}
