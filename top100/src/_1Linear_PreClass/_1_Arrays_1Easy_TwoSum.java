package _1Linear_PreClass;

import java.util.Arrays;
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

	//two pointers, O(nlogn)
	public boolean twoSum(int[] nums, int target){
		if(nums == null || nums.length < 2){
			return false;
		}
		Arrays.sort(nums);//O(nlog(n))
		int start = 0;
		int end = nums.length - 1;
		while(start < end){
			//case1: found
			if(nums[start] + nums[end] == target) {
				return true;
			} else if (nums[start] + nums[end] > target){//case2: larger than target: move end pointer
				end--;
			} else{//case3: smaller than target: move start pointer
				start++;
			}
		}
		return false;
	}
}
//follow up: 3 Sum