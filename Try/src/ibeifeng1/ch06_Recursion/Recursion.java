package ibeifeng1.ch06_Recursion;

import org.junit.Test;

public class Recursion {
	public static void main(String[] args) {

		test2(100);
	}
	@Test
	public  void test() {
		System.out.println("Hello,World!");
		test();
	}

	public static void test2(int n) {
		if(n == 0) {
			return;
		}
		System.out.println(n); 
		test2(n - 1);
	}
}
