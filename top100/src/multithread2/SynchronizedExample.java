package multithread2;
//Synchronized
//synchronized method is equivalent to synchronized(this).
/*Synchronized is reentrant to the same thread. 
 * Here foo() calls bar() which is also protected by
synchronized(this). A thread T that enters foo can enter bar as well. 
Other threads will be blocked if foo() is executed by T.*/
public class SynchronizedExample {
	private static final long SLEEP_INTERVAL_MS = 5000;
	
	public synchronized void foo() {
		System.out.println("Inside foo");
		try {
			Thread.sleep(SLEEP_INTERVAL_MS);
		} catch (InterruptedException e) {
			// 
		}
	}
	
	public synchronized void bar() {
		System.out.println("Inside bar");
	}
	public void foobar() {
		System.out.println("Inside foobar");
	}
	public static void main(String[] args) throws InterruptedException{
		final SynchronizedExample s = new SynchronizedExample();
		
		Thread T1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s.foo();
			}
		});

		Thread T2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s.bar();
			}
		});
		
		Thread T3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s.foobar();
			}
		});
		
		T1.start();
		T2.start();
		T3.start();
		
		T1.join();
		T2.join();
		T3.join();
				
	}
	
}
