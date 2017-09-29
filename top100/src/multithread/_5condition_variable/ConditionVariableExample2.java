package multithread._5condition_variable;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionVariableExample2 {
	private static final long SLEEP_INTERVAL_MS = 1000;
	private boolean running = true;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	
	public void start() {
		Thread thread = new Thread((Runnable) () -> {
			print("Hello, world!");
			try {
				Thread.sleep(SLEEP_INTERVAL_MS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			try {
				lock.lock();
				running = false;
				condition.signalAll();
			} finally {
				lock.unlock();
			}
		});
		thread.start();
		
	}
	
	public void join() throws InterruptedException {
		try {
			lock.lock();
			while(running) {
				print("Waiting for the peer thread to finish.");
				condition.await();
			}
			print("Peer thread finished.");
		} catch (Exception e) {
			lock.unlock();
		}
	}
	
	private void print(String s) {System.out.println(s);}
	
	public static void main(String[] args) throws InterruptedException {
		ConditionVariableExample2 cve = new ConditionVariableExample2();
		cve.start();
		cve.join();
	}
}
