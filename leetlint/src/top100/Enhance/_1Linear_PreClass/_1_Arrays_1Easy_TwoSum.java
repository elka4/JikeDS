package top100.Enhance._1Linear_PreClass;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-sum/

public class _1_Arrays_1Easy_TwoSum {
	public int[] twosum(int[] nums, int target) {
		int[] res = new int[2];
		if (nums == null || nums.length < 2) {
			return res;
		}
		//<remain, index>
		Map<Integer, Integer> remainSet = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (remainSet.containsKey(nums[i])) {
				res[0] = remainSet.get(nums[i]);
				res[1] = i;
				return res;
			} else {
				remainSet.put(target - nums[i], i);
			}
		}
		return res;
	}
}
//follow up: 3 Sum