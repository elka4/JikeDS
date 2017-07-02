package multithread;

import java.util.concurrent.TimeUnit;
//Volatile Variable
//stopRequested needs to be volatile for its change to be seen by the other thread.
public class StopThread {
	private static volatile boolean stopRequested;
	
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested) {
					//syso
					//Thread.sleep(1000);
					i++;
				}
			}
		});
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}

}
