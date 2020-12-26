package com.java.ds.concurrency;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

	Semaphore gateKeeper = new Semaphore(1);

	public static void main(String[] args) {
		TestSemaphore test = new TestSemaphore();
		new Thread(() -> test.execute(),"custom-thread-1").run();

		new Thread(() -> test.execute(), "custom-thread-2").run();

	}

	private void execute() {
		System.out.println("Entrance gate of : "+Thread.currentThread().getName());
		try {
			gateKeeper.acquire();
			System.out.println("Inside gate of : "+Thread.currentThread().getName());
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			gateKeeper.release();
			System.out.println("Exit gate of : "+Thread.currentThread().getName());
		}
	}
}
