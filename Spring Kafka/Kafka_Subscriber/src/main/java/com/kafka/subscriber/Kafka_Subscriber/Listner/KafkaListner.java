package com.kafka.subscriber.Kafka_Subscriber.Listner;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaListner {

    @KafkaListener(topics = "topic1", groupId = "Test1")
    public void listen(String msg){
        System.out.println("listneing to group Test1 and messege :"+msg);
    }
}
