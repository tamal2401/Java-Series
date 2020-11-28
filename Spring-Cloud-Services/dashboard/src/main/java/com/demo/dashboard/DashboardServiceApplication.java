package com.demo.dashboard;

import com.demo.dashboard.config.actuator.heartbeat.HeartBeatEndpoint;
import com.demo.dashboard.config.actuator.logging.LoggingEndpoint;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class DashboardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardServiceApplication.class, args);
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public LoggingEndpoint getLoggingEndpoint() {
        return new LoggingEndpoint();
    }

    @Bean
    public HeartBeatEndpoint getHeartBeatEndpoint() {
        return new HeartBeatEndpoint();
    }
}
