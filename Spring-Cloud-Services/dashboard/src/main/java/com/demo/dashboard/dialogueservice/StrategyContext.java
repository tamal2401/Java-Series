package com.demo.dashboard.dialogueservice;

public class StrategyContext {

    private AbstractStrategy strategy;

    public StrategyContext(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    public String execute(){
        return this.strategy.generateMessage();
    }
}
