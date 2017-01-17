package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


class Happiness2{
	HashMap<Integer, Boolean> cache;
	public Happiness2() {
		cache = new HashMap<Integer, Boolean>();
		cache.put(1, true);
	}
	boolean isHappy(int n, int depth) {
		if (cache.containsKey(n)) {
			System.out.println(depth);
			return cache.get(n);
		}
		cache.put(n, false);
		
		boolean result = isHappy(transform(n), depth + 1);
		cache.replace(n, result);
		return result;
	}
	int transform (int n) {
		int result = 0;
		while (n != 0) {
			result += (n%10)*(n%10);
			n = n / 10;
		}
		return result;
	}
}

public class _1Happy_SaveSpace {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input.txt"));
		Happiness2 happiness = new Happiness2();
		for (int i = 2; i <= 810; ++i) {
			happiness.isHappy(i, 1);
		}
		int n = in.nextInt();
		while (n != -1) {
			System.out.println(happiness.isHappy(happiness.transform(n),1));
			n = in.nextInt();
		}
		in.close();
	}
}
