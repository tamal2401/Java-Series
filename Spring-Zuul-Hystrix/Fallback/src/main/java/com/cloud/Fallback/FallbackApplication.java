package com.cloud.Fallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FallbackApplication.class, args);
	}

}
