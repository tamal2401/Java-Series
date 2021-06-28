package com.java.ds.concurrency.custom;

import com.java.ds.concurrency.SemaphoreDemo;

public class CustomCountingSemaphore {

    private int permits = 0;

    public CustomCountingSemaphore(int permits) {
        this.permits = permits;
    }
    
    public int availablePermits(){
        return this.permits;
    }

    public boolean acquire() throws InterruptedException {
        while(availablePermits()==0){
            System.out.println("waiting for permits:: "+Thread.currentThread().getName());
            Thread.sleep(1000);
        }
        if(this.permits > 0){
            synchronized (this){
                this.permits--;
            }
            return true;
        }
        return false;
    }

    public boolean release(){
        synchronized(this) {
            this.permits++;
        }
        return true;
    }

    public static void main(String[] args) {
        CustomCountingSemaphore countingSemaphore = new CustomCountingSemaphore(2);
        SemaphoreDemo test = new SemaphoreDemo();
        new Worker(countingSemaphore, "custom-thread-1").start();
        new Worker(countingSemaphore, "custom-thread-2").start();
        new Worker(countingSemaphore, "custom-thread-3").start();
    }
}

class Worker extends Thread {
    CustomCountingSemaphore countingSemaphore;
    public Worker(CustomCountingSemaphore semaphore, String name) {
        super(name);
        this.countingSemaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.println("Entrance gate of : "+Thread.currentThread().getName());
        try {
            countingSemaphore.acquire();
            System.out.println("Inside gate of : "+Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Exit gate of : "+Thread.currentThread().getName());
            countingSemaphore.release();
        }
    }
}
