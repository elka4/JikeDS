package _TwoPointer.TwoPointer_J;

import java.util.Arrays;

/** 443 Two Sum - Greater than target
 * Created by tianhuizhu on 6/28/17.
 */
public class _443_Two_Sum_Greater_than_target {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;
        int count = 0;

        while (left < right) {
            if (nums[left] + nums[right] <= target) {
                left++;
            } else {
                count += right - left;
                right--;
            }
        }

        return count;
    }



}
/*
[LintCode] 443 Two Sum - Greater than target 解题报告
Description
Given an array of integers, find how many pairs in the array such that their sum is bigger than a specific target number. Please return the number of pairs.


Example
Given numbers = [2, 7, 11, 15], target = 24. Return 1. (11 + 15 is the only pair)

Challenge
Do it in O(1) extra space and O(nlogn) time.
 */