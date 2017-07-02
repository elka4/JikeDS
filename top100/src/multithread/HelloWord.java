package multithread;

public class HelloWord {
	public static void main(String[] args) throws InterruptedException{
		Thread thread = new Thread(new PrintHello());
		thread.start();
		thread.join();
		System.out.println("Success!");
	}
	
	private static class PrintHello implements Runnable {
		@Override
		public void run() {
			System.out.println("Hello, world!");
		}
	}
}
