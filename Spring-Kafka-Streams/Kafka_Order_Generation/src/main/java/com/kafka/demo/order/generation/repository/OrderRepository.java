package com.kafka.demo.order.generation.repository;

import com.kafka.demo.order.generation.entity.OrderReceivedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderReceivedEntity, Long> {
}
