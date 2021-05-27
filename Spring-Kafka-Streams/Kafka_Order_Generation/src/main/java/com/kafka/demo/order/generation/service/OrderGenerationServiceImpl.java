package com.kafka.demo.order.generation.service;

import com.kafka.demo.order.generation.channel.OrderDeliveryChannel;
import com.kafka.demo.order.generation.dao.OrderArchivalDao;
import com.kafka.demo.order.generation.entity.OrderReceivedEntity;
import com.kafka.demo.order.generation.model.Commodity;
import com.kafka.demo.order.generation.model.OrderModel;
import com.kafka.demo.order.generation.model.RequestModel;
import com.kafka.demo.order.generation.model.User;
import com.kafka.demo.order.generation.producer.OrderGenerationPublisher;
import com.kafka.demo.order.generation.repository.OrderRepository;
import com.kafka.demo.order.generation.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderGenerationServiceImpl implements OrderGenerationService {

    private static final Logger log = LoggUtil.getLogger(OrderGenerationServiceImpl.class);

    private OrderArchivalDao orderArchivalDao;

    @Value("${topic.orderGeneration}")
    String generationTopicName;

    @Autowired
    OrderGenerationPublisher orderGenerationPublisher;

    @Autowired
    OrderDeliveryChannel channel;

    public OrderGenerationServiceImpl(OrderArchivalDao archivalDao) {
        this.orderArchivalDao = archivalDao;
    }

    @Override
    public OrderModel saveAndPublishOrder(RequestModel reqModel) {
        OrderModel model = new OrderModel();
        buildOrderModel(model, reqModel);

        getUserInformation(model, reqModel.getUserId());

        OrderReceivedEntity orderEntity = this.orderArchivalDao.saveOrder(model);
        model.setOrderId(orderEntity.getId());

        log.info("Order generation request published with order :: {}", model);
        orderGenerationPublisher.send(model, channel.orderGenerationRequested());
        return model;
    }

    private void getUserInformation(OrderModel model, String userId) {
        User currUser = new User();
        currUser.setUserId(userId);
        // Setting mock values. In Prod one can hit user details DB to get the proper information.
        currUser.setUserName("tamalD");
        currUser.setMail("onlyjohn98@gmail.com");

        model.setUser(currUser);
    }


    private void buildOrderModel(OrderModel model, RequestModel reqModel) {
        Commodity comm = new Commodity();
        comm.setCategoryId(reqModel.getCategoryId());
        comm.setCategoryName(reqModel.getCategoryName());
        comm.setQuantity(reqModel.getQuantity());
        comm.setProductName(reqModel.getProductName());

        model.setEventId(UUID.randomUUID().toString());
        model.setPaymentMode(reqModel.getPaymentMode());
        model.setEventType(generationTopicName);
        model.setProcessedDateTime(reqModel.getProcessedDateTime());
        model.setTransactionId(reqModel.getTransactionId());
        model.setCommodity(comm);
    }
}
