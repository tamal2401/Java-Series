package com.kafka.demo.KafkaService.repository;

import com.kafka.demo.KafkaService.entity.OrderReceivedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderReceivedEntity, Long> {
}
