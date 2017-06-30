package ss;

import org.junit.Test;

public class Course14_6_162FindPeakElement {
	public int findPeakLinear(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return 0;
		}
		for (int i = 1; i < n - 1; i++) {
			if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
				return i;
			}
		}
		if (nums[0] > nums[1]) {
			return 0;
		}
		if (nums[n - 1] > nums[n - 2]) {
			return n - 1;
		}
		return 0;
	}
	public int findPeakElement(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return 0;
		}
		int start = 0;
		int end = n - 1;
		int mid;
		while (start < end) {
			mid = (start + end) / 2;
			if (nums[mid] > nums[mid + 1]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	@Test
	public void test() {
		int[] nums = {4, 3, 2, 1, 11, 5, 12, 1, 2, 3, 6, 10, 4, 3, 2, 1};
		int index1 = findPeakLinear(nums);
		System.out.println(index1 + " " + nums[index1]);
		int index2 = findPeakElement(nums);
		System.out.println(index2 + " " + nums[index2]);
	}
}
