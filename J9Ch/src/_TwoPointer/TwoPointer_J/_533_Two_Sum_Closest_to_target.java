package _TwoPointer.TwoPointer_J;

import org.junit.Test;

import java.util.Arrays;

/** 533 Two Sum - Closest to target
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _533_Two_Sum_Closest_to_target {
    /**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;
        int diff = Integer.MAX_VALUE;

        while (left < right) {
            if (nums[left] + nums[right] < target) {
                diff = Math.min(diff, target - nums[left] - nums[right]);
                left++;
            } else {
                diff = Math.min(diff, nums[left] + nums[right] - target);
                right--;
            }
        }

        return diff;
    }

//------------------------------------------------------------------------------
    /**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    public int twoSumCloset2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        if (nums.length == 2) {
            return target - nums[0] - nums[1];
        }

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        int minDiff = Integer.MAX_VALUE;

        while (left < right) {
            int sum = nums[left] + nums[right];
            int diff = Math.abs(sum - target);

            if (diff == 0) {
                return 0;
            }
            if (diff < minDiff ) {
                minDiff = diff;
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return minDiff;
    }
    /*
    与3 sum closest问题相似，通过先对数组排序，再用两个指针的方法，可以满足 O(nlogn) + O(n) ~ O(nlogn)
    的时间复杂度的要求不同的是这里要返回的是diff，所以只用记录minDiif即可。
     */
    @Test
    public void test02(){
        int[] nums= {-1, 2, 1, -4};
        int t = 4;
        System.out.println(twoSumCloset2(nums, t));

    }
//------------------------------------------------------------------------------

}
/*
Given an array nums of n integers, find two integers in nums such that the sum
is closest to a given number, target.

Return the difference between the sum of the two integers and the target.

Example
Given array nums = [-1, 2, 1, -4], and target = 4.
The minimum difference is 1. (4 - (2 + 1) = 1).
 */
