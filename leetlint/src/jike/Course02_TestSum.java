package jike;

import org.junit.Test;

public class Course02_TestSum {
	@Test
	public void testMethod(){
		int n=100;
		Course02_SumExceptionMethod sem=new Course02_SumExceptionMethod(n);
		int sum=sem.sumMethod(1);
		System.out.println(sum);
	}
	@Test
	public void testConstructor(){
		int n=100;
		Course02_SumExceptionConstructor.n=n;
		Course02_SumExceptionConstructor.array=new int[n+1];
		new Course02_SumExceptionConstructor(1);
	}
}
