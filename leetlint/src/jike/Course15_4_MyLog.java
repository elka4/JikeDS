package jike;

import org.junit.Test;

public class Course15_4_MyLog {
	public static final double d = 0.00001;
	public double logCommon(double a, double b) {
		return Math.log(b) / Math.log(a);
	}
	public int count = 0;
	public double logBinary(double a, double b) {
		if (b == 1) {
			return 0;
		} else {
			double start = 0;
			double end = b;
			double mid;
			while (true) {
				count++;
				mid = (start + end) / 2;
				double pow = Math.pow(a, mid);
				if (Math.abs(pow - b) <= d) {
					return isLog(a, mid, b);
				} else if (pow < b) {
					start = mid;
				} else {
					end = mid;
				}
			}
		}
	}
	public double isLog(double a, double mid, double b) {
		long c = Math.round(mid);
		if (Math.pow(a, c) == b) {
			return c;
		} else {
			return mid;
		}
	}
	public double logNewton(double a, double b, double init) {
		if (b == 1) {
			return 0;
		} else {
			double lnA = Math.log(a);
			double x = init;
			double nextX;
			while (true) {
				count++;
				double pow = Math.pow(a, x);
				nextX = x - (pow - b) / (pow * lnA);
				if (Math.abs(Math.pow(a, nextX) - b) <= d) {
					return isLog(a, nextX, b);
				}
				x = nextX;
			}
		}
	}
	@Test
	public void testCommon() {
		System.out.println(logCommon(2, 9));
		System.out.println(logCommon(3, 243));
	}
	@Test
	public void testBinary() {
		System.out.println(logBinary(2, 9));
		System.out.println(logBinary(3, 243));
		System.out.println(logCommon(2, 9));
		System.out.println(logCommon(3, 243));
		System.out.println(count);
	}
	@Test
	public void testNewton() {
		System.out.println(logNewton(2, 9, 4));
		System.out.println(logNewton(3, 243, 10));
		System.out.println(logCommon(2, 9));
		System.out.println(logCommon(3, 243));
		System.out.println(count);
	}
}
