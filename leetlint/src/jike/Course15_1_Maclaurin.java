package jike;

import org.junit.Assert;
import org.junit.Test;

public class Course15_1_Maclaurin {
	public double getE(double n) {
		double a = 1;
		double e = a;
		for (double i = 1; i <= n; i++) {
			a /= i;
			e += a;
		}
		return e;
	}
	@Test
	public void testE() {
		double e = getE(10);
		System.out.println(e);
		System.out.println(Math.E);
		Assert.assertTrue(Math.abs(e - Math.E) <= 0.00001);
	}
	public double getPI(double n) {
		double a = Math.sqrt(3) / 3;
		double pi = a;
		for (double i = 1; i <= n; i++) {
			a = -a / 3;
			pi += a / (2 * i + 1);
		}
		pi *= 6;
		return pi;
	}
	@Test
	public void testPI() {
		double pi = getPI(20);
		System.out.println(pi);
		System.out.println(Math.PI);
		Assert.assertTrue(Math.abs(pi - Math.PI) < 0.00001);
	}
}