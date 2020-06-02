package com.java.ds.concurrency.reactivejava;

import io.reactivex.Observable;

public class BasicObserverExample {

    public static void main(String[] args) {
        Observable<String> observable = Observable.just("basic rxJava example");
    }
}
