package com.spring.reactive.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class StoreDataOnStartUp {

    @Autowired
    MongoRepository repo;

    @Bean
    public void loadData() {
        repo.deleteAll()
                .thenMany(Flux.just("Kaldi's Coffee", "Espresso Roast", "Blue Bottle", "Philz Coffee", "Tim Hortons", "Peet's",
                        "Pike Place", "Café Bustelo", "Death Wish", "Green Mountain")
                        .map(data -> new Coffee(UUID.randomUUID().toString(), data))
                        .flatMap(repo::save))
                        .thenMany(repo.findAll())
                        .subscribe(System.out::println);
    }
}
