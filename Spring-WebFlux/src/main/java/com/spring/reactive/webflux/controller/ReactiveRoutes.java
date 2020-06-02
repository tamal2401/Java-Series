package com.spring.reactive.webflux.controller;

import com.spring.reactive.webflux.model.Coffee;
import com.spring.reactive.webflux.model.CoffeeOrder;
import com.spring.reactive.webflux.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//@Component
public class ReactiveRoutes {

    @Autowired
    OrderService service;

    //@Bean
    RouterFunction<ServerResponse> reoutes(){
        return route(GET("/coffees"), this::getAllCoffee)
                .andRoute(GET("/coffees/{id}"), this::getCoffeebyId)
                .andRoute(GET("/coffees/{id}/orders"), this::getOrders);
    }

    public Mono<ServerResponse> getCoffeebyId(ServerRequest req){
        return ServerResponse.ok().body(service.byId(req.pathVariable("id")), Coffee.class);
    }

    public Mono<ServerResponse> getAllCoffee(ServerRequest req){
        return ServerResponse.ok().body(service.getAllCoffee(), Coffee.class);
    }

    public Mono<ServerResponse> getOrders(ServerRequest req){
        return ServerResponse.ok().body(service.getOrder(req.pathVariable("id")), CoffeeOrder.class);
    }
}
