package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


class Happy {
	HashMap<Integer, Boolean> cache;
	Happy() {
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
	
	int transform(int n) {
		int result = 0;
		while (n != 0) {
			result += (n%10) * (n%10);
			n = n / 10;
		}
		return result;
	}
}
public class _1Happy_Recursion {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("intput.txt"));
		int n = in.nextInt();
		Happy happy = new Happy();
		while (n != -1) {
			System.out.println(happy.isHappy(n, 1));
			n = in.nextInt();
		}
		in.close();
	}
}
