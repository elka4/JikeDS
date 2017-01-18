package jike;

import java.util.Arrays;

public class Course14_3_034Linear {
	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		if (nums == null || nums.length == 0) {
			return result;
		}
		int mid = Arrays.binarySearch(nums, target);
		if (mid < 0) {
			return result;
		}
		int start = mid;
		while (start >= 0 && nums[start] == target) {
			start--;
		}
		start++;
		int end = mid;
		while (end < nums.length && nums[end] == target) {
			end++;
		}
		end--;
		result[0] = start;
		result[1] = end;
		return result;
	}
}
