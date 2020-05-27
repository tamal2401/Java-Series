package com.java.spring.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class EmpployeeProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpployeeProducerApplication.class, args);
	}
}
