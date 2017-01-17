package ch9J.chapter5_DP2;

import java.util.ArrayList;

//方法二
class __16_Maximum_Subarray_III_2 {
/**
* @param nums: A list of integers
* @param k: An integer denote to find k non-overlapping subarrays
* @return: An integer denote the sum of max k non-overlapping subarrays
*/ 
	public static int maxSubArray(ArrayList<Integer> nums, int k) {
	   // write your code
	   int len = nums.size();
	   int[][] f = new int[k+1][len];
	   for (int i = 1; i < k+1; i++) {
	       int sum = 0;
	       for (int j = 0; j < i; j++) {
	           sum += nums.get(j);
	       }
	       f[i][i-1] = sum;
	   }
	   for (int i = 1; i < len; i++) {
	   	f[1][i] = Math.max(f[1][i-1]+nums.get(i), nums.get(i));
	   }
	   
	   for (int i = 2; i < k+1; i++) {
	       for (int n = i;  n< len; n++) {
	           int curMax = f[i][n-1] + nums.get(n);
	           for (int j = i-2; j < n; j++) {
	               if ((f[i-1][j] + nums.get(n)) > curMax) {
	                   curMax = f[i-1][j] + nums.get(n);
	               }
	           }
	           f[i][n] = curMax;
	       }
	   }
	   
	   int res = Integer.MIN_VALUE;
	   for (int i = k-1; i < len; i++){
	       if (f[k][i] > res) {
	           res = f[k][i];
	       }
	   }
	   return res;
	}
}