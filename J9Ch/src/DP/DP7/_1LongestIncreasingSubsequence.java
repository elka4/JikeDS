package DP.DP7;


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

•转移方程:f[j]=max{1, f[i]+1|i<janda[i]<a[j]}
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
public class _1LongestIncreasingSubsequence {

//////////////////////////////////////////////////////////////////
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



//////////////////////////////////////////////////////////////////

    // O(nlogn) Binary Search

    public class Solution2 {
        /**
         * @param nums: The integer array
         * @return: The length of LIS (longest increasing subsequence)
         */
        public int longestIncreasingSubsequence(int[] nums) {
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
    }
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////

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
