package com.cloud.Fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class FallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FallbackApplication.class, args);
	}

	@Bean
	public ObjectMapper createMapper(){
		return new ObjectMapper();
	}
}
