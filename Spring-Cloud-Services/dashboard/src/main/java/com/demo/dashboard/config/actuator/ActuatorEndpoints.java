package com.demo.dashboard.config.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.util.Assert;

import javax.annotation.Nullable;

@Endpoint(id="logging")
public class ActuatorEndpoints {

    @Autowired
    private LoggingSystem loggingSystem;

    @WriteOperation
    public void configureLogLevel(@Selector String packageName,
                                  @Nullable LogLevel configureLogLevel){
        Assert.notNull(packageName, "Package Name must not be empty");
        this.loggingSystem.setLogLevel(packageName, configureLogLevel);
    }
}
