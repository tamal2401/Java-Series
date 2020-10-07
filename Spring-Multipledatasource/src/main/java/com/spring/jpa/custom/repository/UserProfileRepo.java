package com.spring.jpa.custom.repository;

import com.spring.jpa.custom.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Integer> {

}
