package jike;

import org.junit.Test;

import java.util.Arrays;

public class Course14_3_034BinarySearch {
	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		if (nums == null || nums.length == 0) {
			return result;
		}
		int start = searchStart(nums, target);
		if (start == -1) {
			return result;
		} else {
			int end = searchEnd(nums, target);
			result[0] = start;
			result[1] = end;
			return result;
		}
	}
	public int searchStart(int[] nums, int target) {

		if (nums == null || nums.length == 0) {
			return 0;
		}
		int start = 0, end = nums.length - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (target == nums[mid]) {
				if (mid == 0 || nums[mid - 1] < target) {
					return mid;
				} else {
					end = mid - 1;
				}
			} else if (target < nums[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}
	public int searchEnd(int[] nums, int target) {

		if (nums == null || nums.length == 0) {
			return 0;
		}
		int start = 0, end = nums.length - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (target == nums[mid]) {
				if (mid == nums.length - 1 || nums[mid + 1] > target) {
					return mid;
				} else {
					start = mid + 1;
				}
			} else if (target < nums[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}
	@Test
	public void test() {
		int[] nums = {1, 2, 4, 6, 6, 6, 6, 7, 9, 9, 10};
		System.out.println(Arrays.toString(searchRange(nums, 6)));
		System.out.println(Arrays.toString(searchRange(nums, 5)));
	}
}
