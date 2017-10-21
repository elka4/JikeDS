package _05_DFS.Ch_08_Recursion_and_Dynamic_Programming.Q8_01_Triple_Step;

import org.junit.Test;

public class QuestionA {
	
	public static int countWays(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
		}
	}
	
	public static void main(String[] args) {
		int n = 20;
		int ways = countWays(n);
		System.out.println(ways);
	}

	@Test
    public void test01(){
        long start = System.currentTimeMillis();
        //System.out.println(fibonacci(i));
        //System.out.println(countWays(20));
        countWays(30);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(  ": " + time + "ms");
    }

}
