package jike;

import org.junit.Test;

public class Course14_7_153FindMinimum {
	public int findMin(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		int mid = 0;
		while (start < end) {
			if (nums[start] < nums[end]) {
				return nums[start];
			}
			if (end - start == 1) {
				return nums[end];
			}
			mid = (start + end) / 2;
			if (nums[mid] > nums[start]) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return nums[0];
	}
	@Test
	public void test() {
		int[] nums1 = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
		int[] nums2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(findMin(nums1));
		System.out.println(findMin(nums2));
	}
}
