package com.kafka.demo.order.generation.controller;

import com.kafka.demo.order.generation.model.Commodity;
import com.kafka.demo.order.generation.model.GlobalResponse;
import com.kafka.demo.order.generation.model.OrderModel;
import com.kafka.demo.order.generation.model.RequestModel;
import com.kafka.demo.order.generation.service.OrderGenerationService;
import com.kafka.demo.order.generation.service.OrderGenerationServiceImpl;
import com.kafka.demo.order.generation.util.LoggUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class ProducerController {

    private static final Logger log = LoggUtil.getLogger(ProducerController.class);

    @Autowired
    OrderGenerationService orderGenerationService;

    @PostMapping(value = "/api/v1/create/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GlobalResponse publishOrder(@RequestBody @Valid RequestModel reqModel) {
        GlobalResponse res = new GlobalResponse();

        try {
            OrderModel publishedModel = orderGenerationService.saveAndPublishOrder(reqModel);
            if (null != publishedModel) {
                res.setData(publishedModel);
            }
        } catch (Exception ex) {
            log.error("Error occured with following error msg:: {}", ex.getMessage());
            res.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setErrorMsg(ex.getMessage());
        }
        return res;
    }
}
