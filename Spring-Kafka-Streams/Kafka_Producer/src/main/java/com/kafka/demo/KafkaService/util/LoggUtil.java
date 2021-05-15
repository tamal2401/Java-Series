package com.kafka.demo.KafkaService.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class LoggUtil {

    public static Logger getLogger(Class cls){
        return LoggerFactory.getLogger(cls.getName());
    }
}
