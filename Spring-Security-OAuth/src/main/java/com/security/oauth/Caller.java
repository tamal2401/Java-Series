package com.security.oauth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Caller {
    public static void main(String[] args) throws Exception {
        Caller caller = new Caller();
        ExecutorService service = Executors.newFixedThreadPool(5, run -> new Thread(run, "custom-"));

        service.submit(()-> System.out.println(Thread.currentThread().getName()));
    }
}
