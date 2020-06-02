package com.spring.reactive.webflux;

import com.spring.reactive.webflux.model.Coffee;
import com.spring.reactive.webflux.model.CoffeeOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxApplication.class, args);
    }

    // To put in a client module to call server endpoints and fetch the stream of data
    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8080/coffees");
    }

    //@PostConstruct
    private void run() {
        client().get()
                .retrieve()
                .bodyToFlux(Coffee.class)
                .filter(coffee -> coffee.getCoffee().equalsIgnoreCase("tim hortons"))
                .flatMap(coffee -> client().get()
                        					.uri("/orders/{id}", coffee.getId())
                        					.retrieve()
                        					.bodyToFlux(CoffeeOrder.class))
                .subscribe(System.out::println);
    }
}
