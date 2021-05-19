package com.kafka.demo.order.generation.dao;

import com.kafka.demo.order.generation.entity.OrderReceivedEntity;
import com.kafka.demo.order.generation.model.OrderModel;
import com.kafka.demo.order.generation.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderArchivalDaoImpl implements OrderArchivalDao{

    OrderRepository orderRepository;

    public OrderArchivalDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderReceivedEntity saveOrder(OrderModel model) {

        OrderReceivedEntity entity = new OrderReceivedEntity();

        buildReqEntity(model, entity);

        OrderReceivedEntity resEntity = orderRepository.save(entity);

        return resEntity;
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
        entity.setUserName(model.getUser().getUserName());
        entity.setUserMailId(model.getUser().getMail());
    }
}
