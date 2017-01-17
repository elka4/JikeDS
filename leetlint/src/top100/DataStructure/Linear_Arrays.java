package top100.DataStructure;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Linear_Arrays {
	int[] A;
	int[] B;
	@Before
	public void PreparedStatement() {
		A = new int[10];
		B = new int[10];
		for (int i = 0; i < A.length; i++) {
			A[i] = i;
		}

	}
	@Test
	public void testArraysCopyOf () {
		//Arrays.copyOf 底层用的是System.arraycopy
		B = Arrays.copyOf(A, 10);
		for (int i : B) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testSystemCopy () {
		//package java.lang; native method
		System.arraycopy(A, 0, B, 0,10);
		for (int i : B) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testClone () {
		//package java.lang; Object method
		B = A.clone();
		for (int i : B) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testAsList() {
		//List<Integer> list = Arrays.asList(1, 2, 3);
		//System.out.println(list);
		//int[] C = new int[10];
		Integer[] C = new Integer[10];
		Integer[] integers = new Integer[] {1,2,3,4,5};
		for (int i = 0; i < A.length; i++) {
			C[i] = i;
		}
		
		//Integer[] values = { 1, 3, 7 };
		//List<Integer> list = Arrays.asList(values);
		
		
		List list2 = Arrays.asList(C); 
		System.out.println(list2.size());
		System.out.println(list2);
		//for (Integer e : list2) {
			
		//}
		//System.out.println(list2.get(0));

	}
	@Test
	public void testCollectionsSort () {
		//Collections.sort();
	}
	
	
}
