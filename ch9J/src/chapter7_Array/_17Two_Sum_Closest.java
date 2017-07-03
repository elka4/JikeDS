package ch9J.chapter7_Array;

import java.util.Arrays;

public class _17Two_Sum_Closest {
	/**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    public int twoSumCloset(int[] nums, int target) {
        // Write your code here
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int diff = Integer.MAX_VALUE;
        while (i < j) {
            if (nums[i] + nums[j] < target) {
                if (target - nums[i] - nums[j] < diff)
                    diff = target - nums[i] - nums[j];
                i ++;
            } else {
                if (nums[i] + nums[j] - target < diff)
                    diff = nums[i] + nums[j] - target;
                j --;
            }
        }
        return diff;
    }
}

/*Given an array nums of n integers, find two integers in nums
 *  such that the sum is closest to a given number, target.
 

Return the difference between the sum of the two integers and 
the target.

Have you met this question in a real interview? Yes
Example
Given array nums = [-1, 2, 1, -4], and target = 4.

The minimum difference is 1. (4 - (2 + 1) = 1).

Challenge 
Do it in O(nlogn) time complexity.

Tags 
Two Pointers Sort
Related Problems 
Medium 3Sum Closest 30 %
Medium Two Sum 28 %*/