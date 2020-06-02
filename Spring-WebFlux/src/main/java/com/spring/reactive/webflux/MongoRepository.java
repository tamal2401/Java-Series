package com.spring.reactive.webflux;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MongoRepository extends ReactiveCrudRepository<Coffee, String> {
}
