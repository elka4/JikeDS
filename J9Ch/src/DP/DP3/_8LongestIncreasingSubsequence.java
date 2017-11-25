package DP.DP3;
import StdLib.In;
import org.junit.Test;
import java.util.*;

//• 坐标型

/*

-----------------------------------------------------------------------------------------------
LintCode76 LongestIncreasingSubsequence

• 题意:
• 给定a[0], ..., a[n-1]
• 找到最长的子序列0<=i1<i2<...<iK<n,使得a[i1]<a[i2]<...<a[iK]，输出K • 例子:
• 输入:[4, 2, 4, 5, 3, 7]
• 输出:4 (子序列2, 4, 5, 7)
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:对于最优的策略，一定有最后一个元素a[j]
• 第一种情况:最优策略中最长上升子序列就是{a[j]}，答案是1
• 第二种情况，子序列长度大于1，那么最优策略中a[j]前一个元素是a[i], 并且a[i] < a[j]
• 因为是最优策略，那么它选中的以a[i]结尾的上升子序列一定是最长的
-----------------------------------------------------------------------------------------------
子问题
• 因为不确定最优策略中a[j]前一个元素a[i]是哪个，需要枚举每个i
• 求以a[i]结尾的最长上升子序列
• 本来是求以a[j]结尾的最长上升子序列
• 化为子问题: i < j
• 状态:设f[j] =以a[j]结尾的最长上升子序列的长度
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• f[j] = 以a[j]结尾的最长上升子序列的长度

f[j] = max{1, f[i]+1 | i<j and a[i]<a[j]}
f[j]：                       以a[j]结尾的最长上升子序列的长度
1：                          情况1： 子序列就是a[j]本身
f[i]+1 | i<j and a[i]<a[j]： 情况2：以a[i]结尾的最长上升子序列的长度，加上a[j]

情况2必须满足
i >= 0
a[j] > a[i]

答案： max{f[0], f[1], f[2], ..., f[n-1]}

-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 情况2必须满足: – i>=0
– a[j] > a[i], 满足单调性 • 初始条件:空
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[j] =以a[j]结尾的最长上升子序列的长度 • 计算f[0], f[1], f[2], ..., f[n-1]
• 答案是max{f[0], f[1], f[2], ..., f[n-1]}
• 算法时间复杂度O(n2)，空间复杂度O(n)

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


//  300. Longest Increasing Subsequence
//  https://leetcode.com/problems/longest-increasing-subsequence/description/
//  http://www.lintcode.com/zh-cn/problem/longest-increasing-subsequence/
public class _8LongestIncreasingSubsequence {
    //https://leetcode.com/problems/longest-increasing-subsequence/solution/
    //https://leetcode.com/articles/longest-increasing-subsequence/

    //Approach #1 Brute Force [Time Limit Exceeded]
    class Solution01{
        public int lengthOfLIS(int[] nums){

            return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
        }
        public int lengthOfLIS(int[] nums, int prev, int curpos){
            if (curpos == nums.length) {
                return 0;
            }
            int taken = 0;
            if (nums[curpos] > prev) {
                taken = 1 + lengthOfLIS(nums, nums[curpos], curpos + 1);
            }
            int nottaken = lengthOfLIS(nums, prev, curpos + 1);
            return Math.max(taken, nottaken);
        }
    }


    //Approach #2 Recursion with memorization [Memory Limit Exceeded]
    public class Solution02 {
        public int lengthOfLIS(int[] nums) {
            int memo[][] = new int[nums.length + 1][nums.length];
            for (int[] l : memo) {
                Arrays.fill(l, -1);
            }
            return lengthofLIS(nums, -1, 0, memo);
        }
        public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
            if (curpos == nums.length) {
                return 0;
            }
            if (memo[previndex + 1][curpos] >= 0) {
                return memo[previndex + 1][curpos];
            }
            int taken = 0;
            if (previndex < 0 || nums[curpos] > nums[previndex]) {
                taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
            }

            int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
            memo[previndex + 1][curpos] = Math.max(taken, nottaken);
            return memo[previndex + 1][curpos];
        }
    }

    //Approach #3 Dynamic Programming [Accepted]
    //其实这个没有九章的解法好理解
    public class Solution03 {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxans = 1;

            for (int i = 1; i < dp.length; i++) {
                int maxval = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        maxval = Math.max(maxval, dp[j]);
                    }
                }
                dp[i] = maxval + 1;                      //要好好理解 +1
                maxans = Math.max(maxans, dp[i]);
            }
            return maxans;
        }
    }
    /*
    Complexity Analysis
    Time complexity : O(n^2). Two loops of nn are there.
    Space complexity : O(n). dp array of size n is used.
     */



    //Approach #4 Dynamic Programming with Binary Search[Accepted]:
    public class Solution04 {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }
//------------------------------------------------------------------------------

    // 9Ch
    public int longestIncreasingSubsequence1(int[] nums) {
        int []f = new int[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // f[i] = f[i] > f[j] + 1 ? f[i] : f[j] + 1;
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            if (f[i] > max) {
                max = f[i];
            }
        }
        return max;
    }
//------------------------------------------------------------------------------

    // 9Ch DP
    public int longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n];
        int j, i;
        int res = 0;

        for (j = 0; j < n; j++) {
            //case 1
            f[j] = 1;

            //case 2
            for (i = 0; i < j; i++) {
                if (nums[i] < nums[j] && f[i] + 1 > f[j]) {     //要好好理解 +1
                    f[j] = f[i] + 1;
                }
            }
            res = Math.max(res, f[j]);
        }
        return  res;
    }

    /*
    给出 [5,4,1,2,3]，LIS 是 [1,2,3]，返回 3
    给出 [4,2,4,5,3,7]，LIS 是 [2,4,5,7]，返回 4
     */

    @Test
    public void test01(){
        int[] A = {5,4,1,2,3};
        int[] B = {4,2,4,5,3,7};
        System.out.println(longestIncreasingSubsequence(A));
        System.out.println(longestIncreasingSubsequence(B));
    }

    //重写九章给的算法，首先把ij互换
    public int longestIncreasingSubsequence_J1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n];
        int res = 0;

        for (int i = 0; i < n; i++) {
            //case 1
            f[i] = 1;

            //case 2
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {     //j指向的是前面的数字，i指向的是后面的数字
                    //f[j]: 以f[j]结尾的最长上升子序列的长度
                    //f[i]：不取nums[j]； f[j] + 1：取nums[j]， 而nums[j] < nums[i]， nums[i]指的是1
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }
        return  res;
    }


//------------------------------------------------------------------------------
    // 9Ch
    // O(nlogn) Binary Search

    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence2(int[] nums) {
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            // find the first number in minLast >= nums[i]
            int index = binarySearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }

        for (int i = nums.length; i >= 1; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }

        return 0;
    }

    // find the first number > num
    private int binarySearch(int[] minLast, int num) {
        int start = 0, end = minLast.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (minLast[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return end;
    }
//------------------------------------------------------------------------------

}

/*
 Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */


/*
Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Have you met this question in a real interview? Yes
Clarification
What's the definition of longest increasing subsequence?

The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Example
For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
For [4, 2, 4, 5, 3, 7], the LIS is [2, 4, 5, 7], return 4
 */

/*
给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度。

您在真实的面试中是否遇到过这个题？ Yes
说明
最长上升子序列的定义：

最长上升子序列问题是在一个无序的给定序列中找到一个尽可能长的由低到高排列的子序列，这种子序列不一定是连续的或者唯一的。
https://en.wikipedia.org/wiki/Longest_increasing_subsequence

样例
给出 [5,4,1,2,3]，LIS 是 [1,2,3]，返回 3
给出 [4,2,4,5,3,7]，LIS 是 [2,4,5,7]，返回 4


 */