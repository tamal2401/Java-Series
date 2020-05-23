package com.javainuse;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import com.javainuse.controllers.ConsumerControllerClient;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class SpringBootHelloWorldApplication {

	public static void main(String[] args)  {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate loadBalanced(){
		return new RestTemplate();
	}
}