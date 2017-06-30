package top100.multithread;
//race needs to be an atomic variable. 
//Volatile can only guarantee that when reading race, 
//the value is up to date.
public class VolatileTest {
	public static volatile int race = 0;
	private static final int THREADS_COUNT = 20;
	
	public static void increase() {
		race++;//not atomic op
		//main memory -> work memory, work memory increment, 
		//write the data back to memory
	}
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		
		for (int i = 0; i < THREADS_COUNT; ++i) {
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int j = 0; j < 10000; ++j) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		
		while(Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(race);
	}
			
}
