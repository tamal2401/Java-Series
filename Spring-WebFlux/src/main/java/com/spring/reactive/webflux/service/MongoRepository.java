package com.spring.reactive.webflux.service;

import com.spring.reactive.webflux.model.Coffee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MongoRepository extends ReactiveCrudRepository<Coffee, String> {
}
