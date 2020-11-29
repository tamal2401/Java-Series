package com.demo.dashboard.dialogueservice;

public interface StrategyBuilder {

    Object execute() throws UnresponsiveServiceException;
}
