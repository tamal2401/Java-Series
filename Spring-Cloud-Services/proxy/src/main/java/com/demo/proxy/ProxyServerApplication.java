package com.demo.proxy;

import com.demo.proxy.filters.LoggingFilter;
import com.demo.proxy.filters.PostFilter;
import com.demo.proxy.filters.RoouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyServerApplication.class, args);
	}

	@Bean
	public LoggingFilter prefilter(){
		return new LoggingFilter();
	}

	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}

	@Bean
	public RoouteFilter routeFilter(){
		return new RoouteFilter();
	}
}
