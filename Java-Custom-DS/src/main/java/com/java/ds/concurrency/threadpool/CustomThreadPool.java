package com.java.ds.concurrency.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Custom Thread Pool implementation. Where all new tasks are queued in a BlockingQueue and once new
 * task comes in a watcher queued the task in the blocking queue and if there is any available threads 
 * in the pool it picks up the task.
 * 
 * @author Tapu
 *
 */
public class CustomThreadPool {

	private final BlockingQueue<Runnable> workerQueue;
	private Thread[] workerThread;

	public CustomThreadPool(int numOfThreads) {
		workerQueue = new LinkedBlockingDeque<Runnable>();
		workerThread = new Thread[numOfThreads];
	}

	public static void main(String[] args) {
		Thread th = new Thread(()->System.out.println("hi"));
		th.run();
	}

	class Worker extends Thread {
		public Worker(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			try {
				workerQueue.take().run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
