/**
 * CompletableFuture Example of using JAVA8 library
 */
package com.java.ds.concurrency.asynchronus;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureImpl {

    public static void main(String[] args) throws Exception {

        CompletableFuture.supplyAsync(()->generateData())
                            .thenApply(data -> data*2)
                            .thenAccept(data -> System.out.println(data));

        CompletableFuture<Integer> future = new CompletableFuture<>();
        future.thenApply(data -> data+10)
                .thenAccept(data -> System.out.println(data));

        System.out.println(future.isCancelled());
        System.out.println((future.isDone()));
        future.complete(10);
        System.out.println(future.isCancelled());
        System.out.println((future.isDone()));
    }

    private static int generateData() {
        return 2;
    }
}
