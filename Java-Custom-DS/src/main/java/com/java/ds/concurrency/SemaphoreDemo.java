package com.java.ds.concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	Semaphore gateKeeper = new Semaphore(1);

	public static void main(String[] args) {
		SemaphoreDemo test = new SemaphoreDemo();
		new Thread(() -> test.execute(5000), "custom-thread-1").start();

		new Thread(() -> test.execute(2000), "custom-thread-2").start();

	}

	private void execute(long timeLimit) {
		System.out.println("Entrance gate of : "+Thread.currentThread().getName());
		try {
			gateKeeper.acquire();
			System.out.println("Inside gate of : "+Thread.currentThread().getName());
			Thread.sleep(timeLimit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Exit gate of : "+Thread.currentThread().getName());
			gateKeeper.release();
		}
	}
}
