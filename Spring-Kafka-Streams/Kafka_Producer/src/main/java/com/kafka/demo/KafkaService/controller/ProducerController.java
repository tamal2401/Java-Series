package com.kafka.demo.KafkaService.controller;

import com.kafka.demo.KafkaService.model.Commodity;
import com.kafka.demo.KafkaService.model.OrderModel;
import com.kafka.demo.KafkaService.model.RequestModel;
import com.kafka.demo.KafkaService.service.OrderGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class ProducerController {

    @Value("${topic.orderGeneration}")
    String generationTopicName;

    @Autowired
    OrderGenerationService orderGenerationService;

    @PostMapping(value = "/api/v1/create/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void publishOrder(@RequestBody @Valid RequestModel reqModel){

        OrderModel model = new OrderModel();
        buildOrderModel(model, reqModel);

        orderGenerationService.saveAndPublishOrder(model);
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
