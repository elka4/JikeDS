package _05_DFS.Ch_08_Recursion_and_Dynamic_Programming.Q8_01_Triple_Step;

import org.junit.Test;

import java.util.Arrays;

public class QuestionB {

	public static int countWays(int n) {
		int[] map = new int[n + 1];
		Arrays.fill(map, -1);
		return countWays(n, map);
	}
	
	public static int countWays(int n, int[] memo) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			return memo[n];
		} else {
			memo[n] = countWays(n - 1, memo)
                    + countWays(n - 2, memo)
                    + countWays(n - 3, memo);
			return memo[n];
		}
	}
	
	public static void main(String[] args) {
		int n = 50;
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