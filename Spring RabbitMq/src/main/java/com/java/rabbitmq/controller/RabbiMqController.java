package com.java.rabbitmq.controller;

import com.java.rabbitmq.domainobject.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbiMqController {

    @PostMapping(value="/post/message", produces = "application/json")
    public String sendMessage(@RequestBody Book book){
        return "";
    }
}
