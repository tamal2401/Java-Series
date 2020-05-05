package com.java.rabbitmq.controller;

import com.java.rabbitmq.domainobject.Book;
import com.java.rabbitmq.producerservice.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbiMqController {

    @Autowired
    ProducerService template;

    @PostMapping(value="/post/message", produces = "application/json")
    public String sendMessage(@RequestBody Book book){
        try{
            template.sendMessage(book);
        }catch(Exception e){
            System.out.println("Exception while queing messege :: "+e.getMessage());
        }
        return "Messege sent";
    }
}
