package com.demo.dashboard.dialogueservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InsultStrategyImpl implements AbstractStrategy {

    @Autowired
    InsultService insultService;

    @Override
    public Object generateMessage() throws IOException {
        return insultService.call();
    }
}
