package com.java.stalker.repo;

import com.java.stalker.model.crud.EnrolledUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepositoryDao extends CrudRepository<EnrolledUser, Long> {

    EnrolledUser findByUserId(String userId);
}