package com.kafka.demo.KafkaService.service;

import com.kafka.demo.KafkaService.channel.OrderDeliveryChannel;
import com.kafka.demo.KafkaService.entity.OrderReceivedEntity;
import com.kafka.demo.KafkaService.model.OrderModel;
import com.kafka.demo.KafkaService.producer.OrderGenerationPublisher;
import com.kafka.demo.KafkaService.repository.OrderRepository;
import com.kafka.demo.KafkaService.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderGenerationServiceImpl implements OrderGenerationService {

    private static final Logger log = LoggUtil.getLogger(OrderGenerationServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    OrderGenerationPublisher orderGenerationPublisher;

    @Autowired
    OrderDeliveryChannel channel;

    public OrderGenerationServiceImpl(OrderRepository repository) {
        this.orderRepository = repository;
    }

    @Override
    public void saveAndPublishOrder(OrderModel model) {
        OrderReceivedEntity entity = new OrderReceivedEntity();

        buildReqEntity(model, entity);

        OrderReceivedEntity orderEntity = this.orderRepository.save(entity);

        model.setOrderId(orderEntity.getId());
        log.info("Order generation request published with order :: {}", model);
        orderGenerationPublisher.send(model, channel.orderGenerationRequested());
    }

    private void buildReqEntity(OrderModel model, OrderReceivedEntity entity) {
        entity.setEventId(model.getEventId());
        entity.setPaymentMode(model.getPaymentMode());
        entity.setProcessedDateTime(model.getProcessedDateTime());
        entity.setTransactionId(model.getTransactionId());
        entity.setCategoryId(model.getCommodity().getCategoryId());
        entity.setCategoryName(model.getCommodity().getCategoryName());
        entity.setQuantity(model.getCommodity().getQuantity());
        entity.setProductName(model.getCommodity().getProductName());
    }
}
