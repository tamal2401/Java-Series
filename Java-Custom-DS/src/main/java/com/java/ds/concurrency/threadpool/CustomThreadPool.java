package com.java.ds.concurrency.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

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
	private boolean isStopped = false;

	public CustomThreadPool(int numOfThreads) {
		workerQueue = new LinkedBlockingDeque<>();
		workerThread = new Thread[numOfThreads];
		int i = 0;

		for(Thread eachThread:workerThread) {
			eachThread = new Worker("custom-thread-"+i);
			workerThread[i]=eachThread;
			i++;
			eachThread.start();
		}
	}

	public synchronized void execute(Runnable task) {
		if(!isStopped) this.workerQueue.add(task);
	}

	public synchronized void shutDown() {
		this.isStopped = true;
		for(Thread eachThread:workerThread) {
			eachThread.interrupt();	
		}
	}

	class Worker extends Thread {
		public Worker(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			while(!isStopped) {
				try {
					workerQueue.take().run();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		CustomThreadPool pool = new CustomThreadPool(8);
		IntStream.range(0, 5).forEach(digit -> {
			pool.execute(()-> {
				System.out.println("Current running thread is :: "+Thread.currentThread().getName());
				//				try {
				//					Thread.sleep(2000);
				//				} catch (InterruptedException e) {
				//					e.printStackTrace();
				//				}
			});
		});
		pool.shutDown();
		//Thread.sleep(5000);
	}
}
