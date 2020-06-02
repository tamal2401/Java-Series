package com.spring.reactive.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    MongoRepository repo;

    public Mono<Coffee> byId(String id){
        return repo.findById(id);
    }

    public Flux<Coffee> getAllCoffee(){
        return repo.findAll();
    }

    public Flux<CoffeeOrder> getOrder(String id){
        return Flux.<CoffeeOrder>generate(sink -> sink.next(new CoffeeOrder(id, Instant.now())))
                .delayElements(Duration.ofSeconds(1));
    }
}
