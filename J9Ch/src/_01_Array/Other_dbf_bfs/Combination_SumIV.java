package _01_Array.Other_dbf_bfs;

/*
LeetCode – Combination Sum IV (Java)

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Java Solution

This problem is similar to Coin Change. It's a typical dynamic programming problem.
 */
public class Combination_SumIV {
    public int combinationSum4(int[] nums, int target) {
        if(nums==null || nums.length==0)
            return 0;

        int[] dp = new int[target+1];

        dp[0]=1;

        for(int i=0; i<=target; i++){
            for(int num: nums){
                if(i+num<=target){
                    dp[i+num]+=dp[i];
                }
            }
        }

        return dp[target];
    }


//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






}
