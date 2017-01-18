package jike;

import org.junit.Test;

public class Course14_2_035SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int start = 0, end = nums.length - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (target == nums[mid]) {
				return mid;
			} else if (target < nums[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	@Test
	public void test() {
		int[] array = {7, 14, 18, 21, 23, 29, 31, 35};
		System.out.println(searchInsert(array, 7));
		System.out.println(searchInsert(array, 35));
		System.out.println(searchInsert(array, 29));
		System.out.println(searchInsert(array, 22));
		System.out.println(searchInsert(array, 6));
		System.out.println(searchInsert(array, 36));
	}
}
