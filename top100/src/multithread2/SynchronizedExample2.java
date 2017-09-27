package multithread2;

public class SynchronizedExample2 {
	private static final long SLEEP_INTERVAL_MS = 5000;
	//private final Object lcok = new Object();
	public void foo() {
		synchronized (this) {
			System.out.println("Inside foo");
			try {
				Thread.sleep(SLEEP_INTERVAL_MS);
			} catch (InterruptedException e) {
				//
			}
		}
	}
	
	public void bar() {
		synchronized (this) {
			System.out.println("Inside bar");
		}
	}
	
	public void foobar() {
		System.out.println("Inside foobar");
	}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchronizedExample2 s = new SynchronizedExample2();
		
		Thread T1 = new Thread((Runnable) () -> {s.foo();});
		Thread T2 = new Thread((Runnable) () -> {s.bar();});
		Thread T3 = new Thread((Runnable) () -> {s.foobar();});

		T1.start();
		
		T2.start();
		
		T3.start();
		
		T1.join();
		
		T2.join();
		
		T3.join();
	}
}
