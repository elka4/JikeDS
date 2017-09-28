package ss;

import org.junit.Assert;
import org.junit.Test;

public class Course15_2_050Pow {
	private double powLinear(double x, int n) {
		double result = 1;
		for (int i = 1; i <= n; i++) {
			result *= x;
		}
		return result;
	}

	public double myPowLinear(double x, int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		} else {
			if (n > 0) {
				return powLinear(x, n);
			} else {
				return 1 / powLinear(x, -n);
			}
		}
	}

	public static final double d = 0.00001;

	@Test
	public void testLinear() {
		Assert.assertTrue(Math.pow(3, 5) == myPowLinear(3, 5));
		Assert.assertTrue(Math.abs(Math.pow(3, -5) - myPowLinear(3, -5)) <= d);
	}

	private double logNTime(double x, int n) {
		if (n == 1) {
			return x;
		} else {
			double half = logNTime(x, n >>> 1);
			if (n % 2 == 0) {
				return half * half;
			} else {
				return half * half * x;
			}
		}
	}

	public double myPowBinary(double x, int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		} else {
			if (n > 0) {
				return logNTime(x, n);
			} else {
				return 1 / logNTime(x, -n);
			}
		}
	}

	@Test
	public void test() {
		System.out.println(myPowBinary(1, -2147483648));
		Assert.assertTrue(Math.pow(3, 8) == myPowBinary(3, 8));
		Assert.assertTrue(Math.abs(Math.pow(3, -8) - myPowBinary(3, -8)) <= d);
		Assert.assertTrue(Math.pow(3, 9) == myPowBinary(3, 9));
		Assert.assertTrue(Math.abs(Math.pow(3, -9) - myPowBinary(3, -9)) <= d);
	}
}
