package com.security.oauth;

public class Child implements Parent {
    @Override
    public void scold() throws InterruptedException {
        wait();
    }

}
