package _4DP_PreClass;

//53. Maximum Subarray

import org.junit.Test;

public class _2_MaxSubArray {
	public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int curSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            //if curSum < 0, reset head pointer
            curSum = Math.max(nums[i], nums[i] + curSum);

            //update global max when a  new curSum got
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
	}

	@Test
    public void test01(){
	    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
