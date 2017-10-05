package S_5_DP_I.all;

/** 41 Maximum Subarray
 * Created by tianhuizhu on 6/28/17.
 */
public class _41_Maximum_Subarray {

    // Version 1: Greedy
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }

        return max;
    }
    public class Solution1 {

    }

// Version 2: Prefix Sum
    public int maxSubArray2(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        return max;
    }


    public int maxSubArray3(int[] nums) {
        // write your code
        if(nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] global = new int[2];
        int[] local = new int[2];
        global[0] = nums[0];
        local[0] = nums[0];

        for(int i = 1; i < n; i ++) {

            local[i % 2] = Math.max(nums[i], local[(i - 1) % 2] + nums[i]);

            global[i % 2] = Math.max(local[i % 2], global[(i - 1) % 2]);
        }
        return global[(n-1) % 2];
    }


}
/*
Maximum Subarray
Given an array of integers, find a contiguous subarray which has the largest sum.

 Notice

The subarray should contain at least one number.

Have you met this question in a real interview? Yes
Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */
/*
给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。

 注意事项

子数组最少包含一个数

您在真实的面试中是否遇到过这个题？ Yes
样例
给出数组[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 */