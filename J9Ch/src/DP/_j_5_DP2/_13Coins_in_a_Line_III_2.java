package DP._j_5_DP2;
//区间类

public class _13Coins_in_a_Line_III_2 {
/**
  * @param values: an array of integers
  * @return: a boolean which equals to true if the first player will win
  */
 public boolean firstWillWin(int[] values) {
     // write your code here
     int n = values.length;
     int [][]dp = new int[n + 1][n + 1];
     boolean [][]flag =new boolean[n + 1][n + 1];
     
     int sum = 0;
     for(int now : values) 
         sum += now;
     
     return sum < 2*MemorySearch(0,values.length - 1, dp, flag, values);
 }
 
 int MemorySearch(int left, int right, int [][]dp, 
		 		  boolean [][]flag, int []values) {
     
     if(flag[left][right])   
         return dp[left][right];
     flag[left][right] = true;
     if(left > right) {
         dp[left][right] = 0;
     } else if (left == right) {
         dp[left][right] = values[left];
     } else if(left + 1 == right) {
         dp[left][right] = Math.max(values[left], values[right]);
     } else {
         int  pick_left = Math.min(
        		 MemorySearch(left + 2, right, dp, flag, values), 
        		 MemorySearch(left + 1, right - 1, dp, flag, values)) + values[left];
         
         int  pick_right = Math.min(
        		 MemorySearch(left, right - 2, dp, flag, values), 
        		 MemorySearch(left + 1, right - 1, dp, flag, values)) + values[right];
         
         dp[left][right] = Math.max(pick_left, pick_right);    
     }
     return dp[left][right];   
 }
}

/*
There are n coins in a line. Two players take turns to take
a coin from one of the ends of the line until there are no 
more coins left. The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Have you met this question in a real interview? Yes
Example
Given array A = [3,2,2], return true.

Given array A = [1,2,4], return true.

Given array A = [1,20,4], return false.

Challenge 
Follow Up Question:

If n is even. Is there any hacky algorithm that can decide whether 
first player will win or lose in O(1) memory and O(n) time?

Tags 
Dynamic Programming Array Game Theory
Related Problems 
Medium Coins in a Line II 30 %
Medium Coins in a Line 41 %
*/