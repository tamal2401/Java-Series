package com.java.ds.concurrency;

public class ExecutorService {
    public static void main(String[] args) {
        Thread task1 = new Thread(new Task(), "Thread-1");
        task1.start();
        System.out.println("Executing thread is ==> " + Thread.currentThread().getName());
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Executing thread is ====> " + Thread.currentThread().getName());
        }
    }
}
