package _4DP_PreClass;

public class _2_MaxSubArray {
	public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int curSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], nums[i] + curSum);//if curSum < 0, reset head pointer
            maxSum = Math.max(maxSum, curSum);//update global max whena  new curSum got
        }
        return maxSum;
	}
}
