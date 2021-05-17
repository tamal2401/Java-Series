package com.kafka.subscriber.order.confirmation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaOrderConfirmationApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaOrderConfirmationApplication.class, args);
	}

}
