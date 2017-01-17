package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

class Happiness{
	boolean isHappy(int n) {
		HashSet<Integer> cache = new HashSet<Integer>();
		while (cache.add(n)) {
			n = transform(n);
		}
		return n == 1 ? true : false;
	}
	
	int transform (int n) {
		int result = 0;
		while (n != 0) {
			result += (n%10) * (n%10);
			n = n / 10;
		}
		return result;
	}
}

public class _1Happy_Iteration {
	public static void main (String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		Happiness  happiness = new Happiness();
		while (n != -1) {
			System.out.println(happiness.isHappy(n));
			n = in.nextInt();
		}
		in.close();
	}
}
