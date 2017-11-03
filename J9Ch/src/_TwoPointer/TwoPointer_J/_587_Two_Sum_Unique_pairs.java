package _TwoPointer.TwoPointer_J;

import java.util.Arrays;

/** 587 Two Sum - Unique pairs
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _587_Two_Sum_Unique_pairs {
    /**
     * @param nums an array of integer
     * @param target an integer
     * @return an integer
     */
    public int twoSum6(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length < 2)
            return 0;

        Arrays.sort(nums);
        int cnt = 0;
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int v = nums[left] + nums[right];
            if (v == target) {
                cnt ++;
                left ++;
                right --;

                while (left < right && nums[right] == nums[right + 1])
                    right --;

                while (left < right && nums[left] == nums[left - 1])
                    left ++;

            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }

        return cnt;
    }

//////////////////////////////////////////////////////////////////////////////////


}
/*
Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. Please return the number of pairs.
 */