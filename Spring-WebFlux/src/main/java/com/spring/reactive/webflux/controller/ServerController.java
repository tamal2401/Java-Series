package com.spring.reactive.webflux.controller;

import com.spring.reactive.webflux.model.Coffee;
import com.spring.reactive.webflux.model.CoffeeOrder;
import com.spring.reactive.webflux.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coffee")
public class ServerController {

    @Autowired
    OrderService service;

    @GetMapping("/{id}")
    public Mono<Coffee> getCoffeebyId(@PathVariable String id){
        return service.byId(id);
    }

    @GetMapping("/all")
    public Flux<Coffee> getAllCoffee(){
        return service.getAllCoffee();
    }

    @GetMapping(value = "/orders/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CoffeeOrder> getOrders(@PathVariable String id){
        return service.getOrder(id);
    }
}
