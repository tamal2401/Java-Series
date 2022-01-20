package com.java.ds.comparator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Test3 {

    public static void main(String[] args) {
        final CountDownLatch l1 = new CountDownLatch(3);
        Service s1 = new Service("s1", l1);
        Service s2 = new Service("s2", l1);
        Service s3 = new Service("s3", l1);
        List<Service> sList = Arrays.asList(s1, s2, s3);
        Thread r1 = new Routine(sList, l1, "R1");

        final CountDownLatch l2 = new CountDownLatch(3);
        Service s21 = new Service("s21", l2);
        Service s22 = new Service("s22", l2);
        Service s23 = new Service("s23", l2);
        List<Service> s2List = Arrays.asList(s21, s22, s23);
        Thread r2 = new Routine(s2List, l2, "R2");

        r1.run();
        r2.run();
    }
}

class Routine extends Thread{

    private List<Service> workers;
    private final CountDownLatch latch;
    private String thName;

    public Routine(List<Service> workers, CountDownLatch latch, String name) {
        this.workers = workers;
        this.latch = latch;
        this.thName = name;
    }

    @Override
    public void run() {
        try {
            for(Service each: workers){
                each.run();
            }
            latch.await();
            System.out.println("All sub tasks are completed for th: "+thName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Service extends Thread{

    private String name;
    private final CountDownLatch latch;

    public Service(String serviceName, CountDownLatch latch) {
        this.name = serviceName;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}