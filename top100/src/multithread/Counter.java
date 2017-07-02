package multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	private int value;
	
	public synchronized int get() {return value;}
	
	public synchronized void increment() {value++;}
	
	public synchronized int get2() {
		synchronized (this) {
			return value;
		}
	}
	
	public synchronized void increment2() {
		synchronized (this) {
			value++;
		}
	}
	
	private final Lock lock = new ReentrantLock();
	public int get3() {
		try {
			lock.lock();
			return value;
		} finally {
			lock.unlock();
		}
	}
	public void increment3() {
		try {
			lock.lock();
			value++;
		} finally {
			lock.unlock();
			// TODO: handle exception
		}
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
