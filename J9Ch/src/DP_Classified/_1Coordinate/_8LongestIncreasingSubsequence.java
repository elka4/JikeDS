package DP_Classified._1Coordinate;

import org.junit.Test;
import java.util.*;

//  Longest Increasing Subsequence
public class _8LongestIncreasingSubsequence {

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

    /*
    For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
    For [4, 2, 4, 5, 3, 7], the LIS is [2, 4, 5, 7], return 4
     */

    @Test
    public void test01(){
        int[] nums1 = {5, 4, 1, 2, 3};
        int[] nums2 = {4, 2, 4, 5, 3, 7};
        System.out.println(longestIncreasingSubsequence(nums1));
        System.out.println(longestIncreasingSubsequence(nums2));
    }

//-------------------------------------------------------------------------/////////////

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


//-------------------------------------------------------------------------/////////////
    // leetcode
    /*
    Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there
     */

    //Approach #1 Brute Force [Time Limit Exceeded]

    public int lengthOfLIS3(int[] nums) {
        return lengthofLIS3(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthofLIS3(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS3(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS3(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }

    @Test
    public void test03(){
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS3(nums1));
    }


//-------------------------------------------------------------------------/////////////

    //Approach #2 Recursion with memorization [Memory Limit Exceeded]
    public int lengthOfLIS4(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS4(nums, -1, 0, memo);
    }
    public int lengthofLIS4(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS4(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS4(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }




//-------------------------------------------------------------------------/////////////

    //Approach #3 Dynamic Programming [Accepted]
    public int lengthOfLIS5(int[] nums) {
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


    public int lengthOfLIS55(int[] nums) {
        // Base case
        if(nums.length <= 1)
            return nums.length;

        // This will be our array to track longest sequence length
        int T[] = new int[nums.length];

        // Fill each position with value 1 in the array
        for(int i=0; i < nums.length; i++)
            T[i] = 1;


        // Mark one pointer at i. For each i, start from j=0.
        for(int i=1; i < nums.length; i++) {
            for(int j=0; j < i; j++) {
                // It means next number contributes to increasing sequence.
                if(nums[j] < nums[i]) {
                    // But increase the value only if it results in a
                    // larger value of the sequence than T[i]

                    // It is possible that T[i] already has larger value
                    // from some previous j'th iteration
                    if(T[j] + 1 > T[i]) {
                        T[i] = T[j] + 1;
                    }
                }
            }
        }

        // Find the maximum length from the array that we just generated
        int longest = 0;
        for(int i=0; i < T.length; i++)
            longest = Math.max(longest, T[i]);

        return longest;
    }



    public int lengthOfLIS555(int[] nums) {
        if(nums.length <= 1)
            return nums.length;

        int T[] = new int[nums.length];

        for(int i=0; i < nums.length; i++)
            T[i] = 1;

        for(int i=1; i < nums.length; i++) {
            for(int j=0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    if(T[j] + 1 > T[i]) {
                        T[i] = T[j] + 1;
                    }
                }
            }
        }

        int longest = 0;
        for(int i=0; i < T.length; i++)
            longest = Math.max(longest, T[i]);
        return longest;
    }

//-------------------------------------------------------------------------/////////////

    //Approach #4 Dynamic Programming with Binary Search[Accepted]:
    public int lengthOfLIS6(int[] nums) {
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


//-------------------------------------------------------------------------/////////////




//-------------------------------------------------------------------------/////////////



//-------------------------------------------------------------------------/////////////





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