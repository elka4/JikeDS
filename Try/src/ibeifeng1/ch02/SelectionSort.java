package ibeifeng1.ch02;

import org.junit.Test;

public class SelectionSort {
	
	public static void sort(long[] arr) {
		int k = 0;
		long tmp = 0;
		for(int i = 0; i < arr.length - 1; i++) {
			k = i;
			for(int j = i; j < arr.length; j++) {
				if(arr[j] < arr[k]) {
					k = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[k];
			arr[k] = tmp;
		}
	}

	@Test
	public void test(){
		long[] arr = new long[5];
		arr[0] = 34;
		arr[1] = 23;
		arr[2] = 2;
		arr[3] = 1;
		arr[4] = -4;

		System.out.print("[");
		for(long num : arr) {
			System.out.print(num + " ");
		}
		System.out.print("]");
		System.out.println();

		sort(arr);

		System.out.print("[");
		for(long num : arr) {
			System.out.print(num + " ");
		}
		System.out.print("]");
		System.out.println();
	}
}
