package com.demo.dashboard.dialogueservice;

public interface StrategyBuilder {

    CommonMessageModel execute() throws UnresponsiveServiceException;
}
