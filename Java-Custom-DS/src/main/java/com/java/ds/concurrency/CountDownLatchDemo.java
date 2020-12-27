package com.java.ds.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		final CountDownLatch counter = new CountDownLatch(4);
		new Thread(new Service(2000, counter, "service-1")).start();
		new Thread(new Service(2000, counter, "service-2")).start();
		new Thread(new Service(2000, counter, "service-3")).start();
		//new Thread(new Service(2000, counter, "service-4")).start();

		try {
			counter.await(5, TimeUnit.SECONDS);
			System.out.println("All pre tasks are completed");
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}

class Service implements Runnable {
	private final String serviceName;
	private final CountDownLatch latch;
	private final long wait;
	
	public Service(long waitVal, CountDownLatch latch, String serviceName) {
		this.latch = latch;
		this.wait = waitVal;
		this.serviceName = serviceName;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("In service : "+this.serviceName);
			Thread.sleep(this.wait);
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("outside service logic");
		latch.countDown();
	}
}