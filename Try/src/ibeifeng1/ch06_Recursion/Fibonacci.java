package ibeifeng1.ch06_Recursion;

import org.junit.Test;

public class Fibonacci {
	public static int getNumber(int n) {
		if(n == 1) {
			return 0;
		} else if(n == 2){
			return 1;
		} else {
			return getNumber(n - 1) + getNumber(n - 2);
		}
	}

	@Test
	public void test(){
		System.out.println(Fibonacci.getNumber(5));
	}
}
