package com.course.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public void buildTopicOrder(){
        TopicBuilder.name("t.comodity.order").partitions(2).replicas(1).build();
    }

    @Bean
    public void buildTopicOrderreply(){
        TopicBuilder.name("t.comodity.order-reply").partitions(1).replicas(1).build();
    }
}
