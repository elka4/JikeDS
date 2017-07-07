package chapter7_Array;

public class _11Maximum_Subarray_III_DP {
/**
 * @param nums: A list of integers
 * @param k: An integer denote to find k non-overlapping subarrays
 * @return: An integer denote the sum of max k non-overlapping subarrays
 */
public int maxSubArray(int[] nums, int k) {
    if (nums.length < k) {
        return 0;
    }
    int len = nums.length;
    
   
    int[][] globalMax = new int[k + 1][len + 1];
    int[][] localMax = new int[k + 1][len + 1];
    
    for (int i = 1; i <= k; i++) {
        localMax[i][i-1] = Integer.MIN_VALUE;
        //小于 i 的数组不能够partition
        for (int j = i; j <= len; j++) {
            localMax[i][j] = Math.max(localMax[i][j-1], globalMax[i - 1][j-1]) + nums[j-1];
            if (j == i)
                globalMax[i][j] = localMax[i][j];
            else
                globalMax[i][j] = Math.max(globalMax[i][j-1], localMax[i][j]);
        }
    }
    return globalMax[k][len];
}
}
/*Given an array of integers and a number k, 
 * find k non-overlapping subarrays which have the largest sum.
 

The number in each subarray should be contiguous.

Return the largest sum.

 Notice

The subarray should contain at least one number

Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Tags 
LintCode Copyright Dynamic Programming Subarray Array
Related Problems 
Medium Best Time to Buy and Sell Stock III 27 %
Medium Best Time to Buy and Sell Stock II 49 %
Medium Best Time to Buy and Sell Stock 41 %
Medium Maximum Subarray Difference 23 %
Hard Maximum Subarray III 23 %
Medium Maximum Subarray II 24 %
Easy Maximum Subarray 37 %*/