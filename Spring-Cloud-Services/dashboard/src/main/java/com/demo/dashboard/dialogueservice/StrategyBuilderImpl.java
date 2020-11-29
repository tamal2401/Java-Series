package com.demo.dashboard.dialogueservice;

import java.io.IOException;

public class StrategyBuilderImpl implements StrategyBuilder {

    private AbstractStrategy strategy;

    public StrategyBuilderImpl(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    public Object execute() throws UnresponsiveServiceException {
        try{
            return this.strategy.generateMessage();
        }catch(IOException ex){
            throw new UnresponsiveServiceException(ex.getMessage());
        }
    }
}
