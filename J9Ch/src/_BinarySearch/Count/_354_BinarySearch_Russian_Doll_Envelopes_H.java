package _BinarySearch.Count;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

//  354. Russian Doll Envelopes
//  https://leetcode.com/problems/russian-doll-envelopes/description/
//  http://www.lintcode.com/zh-cn/problem/russian-doll-envelopes/
//   Binary Search, Dynamic Programming
public class _354_BinarySearch_Russian_Doll_Envelopes_H {
    //Simple DP solution
    public int maxEnvelopes1(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 ||
                envelopes[0] == null || envelopes[0].length == 0){
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2){
                return Integer.compare(e1[0], e2[0]);
            }
        });

        int   n  = envelopes.length;
        int[] dp = new int[n];
        int ret = 0;

        for (int i = 0; i < n; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (envelopes[i][0] > envelopes[j][0] &&
                    envelopes[i][1] > envelopes[j][1]){
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

//---------------------------------//////////////////////
    // 9Ch
    /*
    1. Sort the array. Ascend on width and descend on height if width are same.
    3. Find the longest increasing subsequence based on height.

    - Since the width is increasing, we only need to consider height.
    - [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting
    otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
     */
    public int maxEnvelopes(int[][] envelopes) {
        // Write your code here
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });

        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);

            if(index < 0)           //没找到
                index = -index - 1; //这个是可以插入的位置，也就是第一个比envelope[1]大的位置

            dp[index] = envelope[1];//因此这样存envelope[1]到dp[index]，必然是升序

            if (index == len)
                len++;
        }
        return len;
    }

    // Arrays.binarySearch 找到
    @Test
    public void test(){
        int[] dp = {1,2,3,3,4,4,4,5};
        for (int i = 0; i < dp.length; i++) {
            int index = Arrays.binarySearch(dp, 0, dp.length, dp[i]);
            System.out.print(index + " ");
        }
        //  0 1 2 3 4 5 6 7 index
        //  1 2 3 3 4 4 4 5 value
        //  0 1 3 3 5 5 5 7 result

    }

//---------------------------------//////////////////////
    //  https://leetcode.com/problems/longest-increasing-subsequence/discuss/
    //  Java/Python Binary search O(nlogn) time with explanation
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;

            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }

            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }
/*   这个是关键！！！

tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].

For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6

We can easily prove that tails is a increasing array.

Therefore it is possible to do a binary search in tails array to find the one needs update.

Each time we only do one of the two:

(1) if x is larger than all tails, append it, increase the size by 1

(2) if tails[i-1] < x <= tails[i], update tails[i]

Doing so will maintain the tails invariant.

The the final answer is just the size.

 */

//---------------------------------//////////////////////

}
/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


 */

/*
给一定数量的信封，带有整数对 (w, h) 分别代表信封宽度和高度。一个信封的宽高均大于另一个信封时可以放下另一个信封。
求最大的信封嵌套层数。

样例
给一些信封 [[5,4],[6,4],[6,7],[2,3]] ，最大的信封嵌套层数是 3([2,3] => [5,4] => [6,7])。


 */