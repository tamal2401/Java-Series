package com.kafka.demo.order.inventory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggUtil {

    public static Logger getLogger(Class cls){
        return LoggerFactory.getLogger(cls.getName());
    }
}
