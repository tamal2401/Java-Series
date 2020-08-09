package com.java.stalker.repo;

import com.java.stalker.model.crud.PostedComments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepositoryDao extends CrudRepository<PostedComments, Long> {
}
