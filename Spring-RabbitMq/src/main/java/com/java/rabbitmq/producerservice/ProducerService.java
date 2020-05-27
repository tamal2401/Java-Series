package com.java.rabbitmq.producerservice;

import com.google.gson.Gson;
import com.java.rabbitmq.domainobject.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private AmqpTemplate mqTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.exchange.routingkey}")
    private String exchangeKey;

    public void sendMessage(Book book){
        mqTemplate.convertAndSend(exchangeName, exchangeKey, book);
        LOG.info("Messege send to the mq successfully : {}".concat(book.toString()));
    }
}
