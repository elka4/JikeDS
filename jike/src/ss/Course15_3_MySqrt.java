package ss;

import org.junit.Assert;
import org.junit.Test;

public class Course15_3_MySqrt {
	public static final double d = 0.00001;
	public int count = 0;
	private double isSquare(double x, double mid) {
		long a = Math.round(mid);
		if (a * a == x) {
			return a;
		} else {
			return mid;
		}
	}
	public double sqrtBinary(double x) {
		if (x == 0 || x == 1) {
			return x;
		} else {
			double start = 0;
			double end = x;
			double mid;
			while (true) {
				count++;
				mid = (start + end) / 2;
				if (mid == x / mid || Math.abs(mid - x / mid) <= d) {
					return isSquare(x, mid);
				} else if (mid < x / mid) {
					start = mid;
				} else {
					end = mid;
				}
			}
		}
	}
	@Test
	public void testBinary() {
		System.out.println(sqrtBinary(2));
		System.out.println(sqrtBinary(3));
		System.out.println(sqrtBinary(4));
		System.out.println(sqrtBinary(5));
		System.out.println(sqrtBinary(9.0));
		Assert.assertTrue(Math.abs(sqrtBinary(80) - Math.sqrt(80)) <= d);
		System.out.println(count);
	}
	public double sqrtNewton(double y0) {
		if (y0 == 0 || y0 == 1) {
			return y0;
		} else {
			double x = y0;
			double nextX;
			while (true) {
				count++;
				nextX = x - (x * x - y0) / (2 * x);
				if (Math.abs(nextX - y0 / nextX) <= d) {
					return isSquare(y0, nextX);
				}
				x = nextX;
			}
		}
	}
	@Test
	public void testNewton() {
		System.out.println(sqrtNewton(2));
		System.out.println(sqrtNewton(3));
		System.out.println(sqrtNewton(4));
		System.out.println(sqrtNewton(5));
		System.out.println(sqrtNewton(9.0));
		Assert.assertTrue(Math.abs(sqrtNewton(80) - Math.sqrt(80)) <= d);
		System.out.println(count);
	}
}
