package ibeifeng1.ch06_Recursion;

import org.junit.Test;

public class Triangle {
	
	public  int getNumber(int n) {
		int total= 0;
		while(n > 0) {
			total = total + n;
			n--;
		}
		return total;
	}
	
	public  int getNumberByRecursion(int n) {
		if(n == 1) {
			return 1;
		} else {
			return n + getNumberByRecursion(n - 1);
		}
	}

	@Test
    public void testGetNumber(){
        System.out.println(getNumber(10));
    }

    @Test
    public void testgetNumberByRecursion(){
        System.out.println(getNumberByRecursion(10));
    }


}
