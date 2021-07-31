package com.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceThreeApplication.class, args);
	}

	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}
}
