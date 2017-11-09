package DP.DP7;
import org.junit.Test;
import java.util.*;

/*
-----------------------------------------------------------------------------------------------
LintCode 76 Longest Increasing Subsequence

• 题意:
• 给定a[0], ..., a[n-1]
• 找到最长的子序列0<=i1<i2<...<iK<n,使得a[i1]<a[i2]<...<a[iK]，输出K
• 例子:
• 输入:[4, 2, 4, 5, 3, 7]
• 输出:4 (子序列2, 4, 5, 7)
-----------------------------------------------------------------------------------------------
题目分析

• 之前课上分析过
• 最长序列型动态规划
• f[j] =以a[j]结尾的最长上升子序列的长度
•转移方程:f[j]=max{1, f[i]+1|i<janda[i]<a[j]}
• 时间复杂度O(N2)
• 能不能继续优化
-----------------------------------------------------------------------------------------------
分析方程f的值

•转移方程:f[j]=max{1, f[i]+1 | i<j and a[i]<a[j]}
• 每个f[j]都在寻找前面比自己小的a[i]里，最大的f[i]

-----------------------------------------------------------------------------------------------
优化要点

• 对于每个f值:1, 2, ..., 记录拥有这个f值的最小的a[i]
– f[1] = 1, a[1] = 1
– f[6] = 2, a[6] = 2
– f[5] = 3, a[5] = 3

• 对于每个f值:1, 2, ..., 记录拥有这个f值的最小的a[i]
    – f[1] = 1, a[1] = 1
    – f[6] = 2, a[6] = 2
    – f[5] = 3, a[5] = 3
• 这个序列(a[1]=1, a[6]=2, a[5]=3)中，一定是每个数都比下一个小
• 一个新的数a[j]来了，它的f值很好算:在序列(a[1]=1, a[6]=2, a[5]=3)中 找到比它小的数中对应f值最大的那个f[i]，f[j] = f[i] + 1
    – a[j]=10, 找到a[5] = 3，所以f[j] = 3 + 1 = 4
    – a[j]=2，找到a[1] = 1 ，所以f[j] = 1 + 1 = 2
• 然后用a[j]替换序列中的下一个，因为f[j]和它值一样，但f[j]更小
-----------------------------------------------------------------------------------------------
二分查找优化

• 在序列(a[1]=1, a[6]=2, a[5]=3)中找到比它小的数中对应f值最大的那个
f[i]，f[j] = f[i] + 1
• 而序列永远是单调增的
• 所以可以二分查找
• 序列长度<=N，因为最长上升子序列长度<=N
• 每次查找时间复杂度O(log2N)
• 总的时间复杂度O(Nlog2N)
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------
 */


//  Longest Increasing Subsequence
//  https://leetcode.com/problems/longest-increasing-subsequence/description/
//  http://www.lintcode.com/zh-cn/problem/longest-increasing-subsequence/
public class _3LongestIncreasingSubsequence {
    //https://leetcode.com/problems/longest-increasing-subsequence/solution/

    //Approach #1 Brute Force [Time Limit Exceeded]

    public class Solution01 {

        public int lengthOfLIS(int[] nums) {
            return lengthofLIS(nums, Integer.MIN_VALUE, 0);
        }

        public int lengthofLIS(int[] nums, int prev, int curpos) {
            if (curpos == nums.length) {
                return 0;
            }
            int taken = 0;
            if (nums[curpos] > prev) {
                taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
            }
            int nottaken = lengthofLIS(nums, prev, curpos + 1);
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
                dp[i] = maxval + 1;
                maxans = Math.max(maxans, dp[i]);
            }
            return maxans;
        }
    }

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


//////////////////////////////////////////////////////////////////
    //9CH DP
    // nlogn
    public int LongestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] b = new int[n + 1];
        // b[i]: when f value is i, smallest a value (ending value)

        int top = 0;
        b[0] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // b[0] ~ b[top]
            // last value b[j] which is smaller than A[i]
            int start = 0, stop = top;
            int mid;
            int j = -1;

            while (start <= stop) {
                mid = (start + stop) / 2;
                if (b[mid] < nums[i]) {
                    j = mid;
                    start = mid + 1;
                } else {
                    stop = mid - 1;
                }
            }

            b[j + 1] = nums[i];
            if (j + 1 > top) {
                top = j + 1;
            }
        }

        // b[0] ... b[top]
        // b[top] stores the smallest ending value for an increasing sequence with length top
        return top;
    }


    /*
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 */
    @Test
    public void test0() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(LongestIncreasingSubsequence(nums));
    }


//////////////////////////////////////////////////////////////////
    // jiuzhang
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n];
        int[] b = new int[n + 1];
        // b[i]: when f value is i, smallest a value (ending value)

        int top = 0;
        b[0] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // b[0] ~ b[top]
            // last value b[j] which is smaller than A[i]
            int start = 0, stop = top;
            int mid;
            int j = -1;

            while (start <= stop) {
                mid = (start + stop) / 2;
                if (b[mid] < nums[i]) {
                    j = mid;
                    start = mid + 1;
                } else {
                    stop = mid - 1;
                }
            }

            // b[j]: length is j (f value is j), smallest ending value
            b[j + 1] = nums[i];
            if (j + 1 > top) {
                top = j + 1;
            }
        }
        // b[0] .... b[top]
        // b[top] stores the smallest ending value for an increasing sequence with length top
        return top;
    }

    /*
    Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
     */
    @Test
    public void test01() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

//////////////////////////////////////////////////////////////////
    // 9Ch
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence1(int[] nums) {
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

    @Test
    public void test02() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(longestIncreasingSubsequence1(nums));
    }

//////////////////////////////////////////////////////////////////
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
    @Test
    public void test03() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(longestIncreasingSubsequence2(nums));
    }

//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////

}
/*  最长上升子序列
给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度。

您在真实的面试中是否遇到过这个题？ Yes
说明
最长上升子序列的定义：

最长上升子序列问题是在一个无序的给定序列中找到一个尽可能长的由低到高排列的子序列，这种子序列不一定是连续的或者唯一的。
https://en.wikipedia.org/wiki/Longest_increasing_subsequence

样例
给出 [5,4,1,2,3]，LIS 是 [1,2,3]，返回 3
给出 [4,2,4,5,3,7]，LIS 是 [2,4,5,7]，返回 4

要求时间复杂度为O(n^2) 或者 O(nlogn)
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

