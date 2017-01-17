package ch9J.chapter4_DP1; //不是动态规划

//1. 判断
//2. 看是哪一类
//3. 四要素
//
//1. state： f[i] 代表从“任意点”出发，跳到坐标i，一共踩过多少个数。
//2. function： f[i] = MAX{f[j]} + 1 {j < i && nums[j] < nums[i]}
//右边点依赖左边点。
//3. initialize f[0..n-1] = 1  :因为本题可以从任何一点出发
//4. answer: max{f[0..n-1]}    ：answer也必须每一点都比较
//


/*
 * Longest Increasing Subsequence
￼￼• 将n个数看做n个木桩,目的是从某个木桩出发,从前向后,从低往高,看做多能踩多少个木桩。 
• state: f[i] 表示(从任意某个木桩)跳到第i个木桩,最多踩过多少根木桩
• function: f[i] = max{f[j] + 1}, 
			j必须满足 j < i && nums[j] <= nums[i]。不过"<=不能AC［1111］"。
• initialize: f[0..n-1] = 1
• answer: max{f[0...n-1]}
 */

public class _7Longest_Increasing_Subsequence {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        int []f = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = f[i] > f[j] + 1 ? f[i] : f[j] + 1;
                }
            }
            if (f[i] > max) {
                max = f[i];
            }
        }
        return max;
    }
}


/*
 * Given a sequence of integers, 
 * find the longest increasing subsequence (LIS).You code should
 *  return the length of the LIS.
Have you met this question in a real interview? Yes
Clarification
What's the definition of longest increasing subsequence? The longest 
increasing subsequence problem is to find a subsequence of a given 
sequence in which the subsequence's elements are in sorted order, 
lowest to highest, and in which the subsequence is as long as possible.
 This subsequence is not necessarily contiguous, or unique.
 
https://en.wikipedia.org/wiki/Longest_increasing_subsequence
Example
For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
For [4, 2, 4, 5, 3, 7], the LIS is [2, 4, 5, 7], return 4
Challenge 
Time complexity O(n^2) or O(nlogn)
Tags 
Binary Search LintCode Copyright Dynamic Programming
 */