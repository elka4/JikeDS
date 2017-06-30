package ss;

import org.junit.Test;

public class Course15_3_069Sqrt {
	public static final int d = 1;
	public int mySqrt(int x) {
		if (x == 0 || x == 1) {
			return x;
		} else {
			int start = 0;
			int end = x;
			int mid;
			while (true) {
				mid = (start + end) / 2;
				if (mid == x / mid || (end - start) <= d) {
					return mid;
				} else if (mid < x / mid) {
					start = mid;
				} else {
					end = mid;
				}
			}
		}
	}
	@Test
	public void test() {
		System.out.println(mySqrt(9));
		System.out.println(mySqrt(25));
		System.out.println(mySqrt(26));
		System.out.println(mySqrt(35));
	}
}
