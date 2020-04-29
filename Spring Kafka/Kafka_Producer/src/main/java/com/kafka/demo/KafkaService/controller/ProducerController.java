package com.kafka.demo.KafkaService.controller;

import com.google.gson.Gson;
import com.kafka.demo.KafkaService.Model.UserTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson jsonConverter;

    @RequestMapping(value="/topic/{topic1}", method = RequestMethod.POST)
    public  void postTopic(@PathVariable String topic1, UserTopic topicContent){
        kafkaTemplate.send(topic1, jsonConverter.toJson(topicContent));
    }
}
