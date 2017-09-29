package multithread._2helloworld;

public class _03HelloWord {
	public static void main(String[] args) throws InterruptedException{
		Thread thread = new Thread(new PrintHello());
		thread.start();
		thread.join();//主线程成thread结束后才继续执行
		System.out.println("Success!");
	}
	
	private static class PrintHello implements Runnable {
		@Override
		public void run() {
			System.out.println("Hello, world!");
		}
	}
}
