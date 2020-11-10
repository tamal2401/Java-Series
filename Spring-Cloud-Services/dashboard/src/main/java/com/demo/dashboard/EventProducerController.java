package com.demo.dashboard;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventProducerController {

    @PostMapping(value = "/api/publish/{topic}")
    public void publishMessege(@PathVariable String topic){

    }
}
