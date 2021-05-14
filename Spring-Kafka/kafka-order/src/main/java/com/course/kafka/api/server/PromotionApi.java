package com.course.kafka.api.server;

import com.course.kafka.api.request.PromotionRequest;
import com.course.kafka.command.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionApi {

    @Autowired
    PromotionService service;

    @PostMapping(value = "/api/promotion", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPromotion(@RequestBody PromotionRequest request) {
        service.createPromotion(request);

        return request.getPromotionCode();
    }
}
