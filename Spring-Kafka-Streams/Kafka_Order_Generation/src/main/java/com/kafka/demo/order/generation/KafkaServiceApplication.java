package com.kafka.demo.order.generation;

import com.kafka.demo.order.generation.channel.OrderDeliveryChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(OrderDeliveryChannel.class)
public class KafkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);
	}

}
