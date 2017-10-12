package DP.DP3;
//• 坐标型


import a.d.M;
import a.j.A;
import org.junit.Test;

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


//  Longest Increasing Subsequence
public class _8LongestIncreasingSubsequence {


///////////////////////////////////////////////////////////////////////////

    // 9Ch DP
    public int longestIncreasingSubsequence(int[] A) {
        int n = A.length;
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
                if (A[i] < A[j] && f[i] + 1 > f[j]) {
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
///////////////////////////////////////////////////////////////////////////


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


///////////////////////////////////////////////////////////////////////////

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
///////////////////////////////////////////////////////////////////////////

}
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