package ch9J.chapter4_DP1;

public class _6Jump_Game_II {

}
/*
 * Jump Game II
￼￼• 最优算法:贪心法 O(n)
• 次优算法:动态规划 O(n^2)
• state: f[i]代表我跳到下标i的位置最少需要几步
• function: f[i] = MIN{f[j]+1} 其中 j < i && j能够跳到i 
• initialize: f[0] = 0
 */

//version 1: Dynamic Programming
class Jump_Game_II1 {
 public int jump(int[] A) {
     int[] steps = new int[A.length];
     steps[0] = 0;
     
     for (int i = 1; i < A.length; i++) {
         steps[i] = Integer.MAX_VALUE;
         for (int j = 0; j < i; j++) {
        	 	//求最小的做法，从能满足条件的j里面取一个最小的（？）
             if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                 steps[i] = steps[j] + 1;
                 break;
             }
         }
     }
     return steps[A.length - 1];
 }
}


//version 2: Greedy
class Jump_Game_II2 {
 public int jump(int[] A) {
     if (A == null || A.length == 0) {
         return -1;
     }
     int start = 0, end = 0, jumps = 0;
     while (end < A.length - 1) {
         jumps++;
         int farthest = end;
         for (int i = start; i <= end; i++) {
             if (A[i] + i > farthest) {
                 farthest = A[i] + i;
             }
         }
         start = end + 1;
         end = farthest;
     }
     return jumps;
 }
}

/*
 * Given an array of non-negative integers, you are initially 
 * positioned at the first index of the array.

Each element in the array represents your maximum jump length 
at that position.

Your goal is to reach the last index in the minimum number of jumps.

Have you met this question in a real interview? Yes
Example
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. 
(Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Tags 
Greedy Array
Related Problems 
Medium Jump Game
 */