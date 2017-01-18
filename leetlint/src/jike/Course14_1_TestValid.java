package jike;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class Course14_1_TestValid {
	public static final int Max = 1 << 20;
	@Test
	public void test() {
		Integer[] array = new Integer[Max];
		Random ra = new Random();
		for (int i = 0; i < Max; i++) {
			array[i] = ra.nextInt();
		}
		Arrays.sort(array);
		Course14_1_BinarySearchUtil<Integer> bs = new Course14_1_BinarySearchUtil<Integer>();
		for (int i = 0; i < Max; i++) {
			int key = ra.nextInt(Max);
			int start = ra.nextInt(Max);
			int end = ra.nextInt(Max);
			if (start <= end) {
				int index1 = bs.binarySearch(array, start, end, key);
				int index2 = Arrays.binarySearch(array, start, end, key);
				Assert.assertTrue(index1 == index2);
			}
		}
	}
}
