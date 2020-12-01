package com.demo.dashboard.dialogueservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MotivationalStrategyImpl implements AbstractStrategy {

    @Autowired
    MotivationService motivationService;

    @Override
    public CommonMessageModel generateMessage() throws IOException {
        return motivationService.call();
    }
}
