package DP.DP2;

//• 坐标型动态规划

/*

• f[j] = 以a[j]结尾的最长连续上升子序列的长度

f[j] = max{ 1, f[j–1]+1| j>0 and a[j-1] < a[j]}

f[j]:  以a[j]结尾的最长连续上升子序列的长度
1:      情况1：子序列本身就是a[j]本身
f[j–1]+1| j>0 and a[j-1] < a[j]: 情况2：以a[j-1]结尾的最长连续上升子序列的长度，加上a[j]

情况2必须满足：
j > 0, 即a[j]前面至少还有一个元素
a[j] > a[j - 1]， 满足单调性

-----------------------------------------------------------------------------------------------
 */


//  lint 397. Longest Increasing Continuous Subsequence
//  674. Longest Continuous Increasing Subsequence  只看一个方向
//  https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/
public class _4LICS {

    //  https://leetcode.com/articles/longest-continuous-increasing-subsequence/
    //  Approach #1: Sliding Window [Accepted]
    public int findLengthOfLCIS1(int[] nums) {
        int ans = 0, anchor = 0;                //双前向指针
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i-1] >= nums[i])
                anchor = i;                     //慢指针在不满足条件的时候重设为i
            ans = Math.max(ans, i - anchor + 1);//update ans
        }
        return ans;
    }



/////////////////////////////////////////////////////////////////////////

    //  674. Longest Continuous Increasing Subsequence  只看一个方向
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] f = new int[n];
        //f[0] = 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            f[i] = 1;                               //初始化为1，就是1个字符
            if (i > 0 && nums[i] > nums[i - 1]){    //计算
                f[i] = f[i - 1] + 1;
            }
            result = Math.max(result , f[i]);       //update result
        }
        return result;
    }

/////////////////////////////////////////////////////////////////////////

    public int longestIncreasingContinuousSubsequence (int[] A){
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int r1 = calc(A, n);
        reverse(A);
        int r2 = calc(A, n);

        return r1 > r2 ? r1 : r2;
    }

    private int calc(int[] A, int n){
        int result = 0;
        int[] f = new int[n];
        int i;
        for (i = 0; i < n; ++i) {
            //初始化
            f[i] = 1;

            //计算状态
            if (i > 0 && A[i - 1] < A[i]) {
                f[i] = f[i - 1] + 1;
            }
//            f[i] = i > 0 && A[i - 1] < A[i] ? f[i - 1] + 1 : 1;

            //计算结果
            result = Math.max(result, f[i]);
        }
        return result;
    }


    private void reverse (int[] A) {
        int n = A.length;
        int t;
        int i = 0;
        int j = n - 1;
        while(i < j){
            t = A[i];
            A[i]  = A[j];
            A[j] = t;
            ++i;
            --j;
        }
    }


    int result = 0;

    void calc1(int[] A, int n){
        int[] f = new int[n];
        int i;
        for (i = 0; i < n; ++i) {
            //option 1
            f[i] = 1;

            //option2
            if (i > 0 && A[i - 1] < A[i]) {
                f[i] = f[i - 1] + 1;
            }

            if (f[i] > result){
                result = f[i];
            }
        }
    }

    void calc2(int[] A, int n){
        int[] f = new int[2];
        int i;
        int old, now = 0;

        for (i = 0; i < n; ++i) {
            old = now;
            now = 1 - now;
            //option 1
            f[now] = 1;

            //option2
            if (i > 0 && A[i - 1] < A[i]) {
                f[now] = f[old] + 1;
            }

            if (f[now] > result){
                result = f[now];
            }
        }
    }
/////////////////////////////////////////////////////////////////////////

    public int longestIncreasingContinuousSubsequence1(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int answer = 1;

        // from left to right
        int length = 1; // just A[0] itself
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }

        // from right to left
        length = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }

        return answer;
    }

/////////////////////////////////////////////////////////////////////////

    //  方法二
    /**
     * @param A an array of Integer
     * @return  an integer
     */

    int LIS(int[] A) {
        int n = A.length;
        int[] f = new int[n];
        int i, res = 0;
        for (i = 0; i < n; ++i) {
            f[i] = 1;
            if (i > 0 && A[i-1] < A[i]) {
                f[i] = f[i-1] + 1;
            }
            if (f[i] > res) {
                res = f[i];
            }
        }

        return res;
    }

    public int longestIncreasingContinuousSubsequence2(int[] A) {
        int n = A.length;
        int r1 = LIS(A);
        int i = 0, j = n-1, t;
        while (i < j) {
            t = A[i];
            A[i] = A[j];
            A[j] = t;
            ++i;
            --j;
        }

        int r2 = LIS(A);

        if (r1 > r2) {
            return r1;
        }
        else {
            return r2;
        }
    }


/////////////////////////////////////////////////////////////////////////
}
/*
给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。
（最长上升连续子序列可以定义为从右到左或从左到右的序列。）

 注意事项

time

您在真实的面试中是否遇到过这个题？ Yes
样例
给定 [5, 4, 2, 1, 3], 其最长上升连续子序列（LICS）为 [5, 4, 2, 1], 返回 4.

给定 [5, 1, 2, 3, 4], 其最长上升连续子序列（LICS）为 [1, 2, 3, 4], 返回 4.
 */
/*
Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:

Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.
 Notice

O(n) time and O(1) extra space.

Have you met this question in a real interview? Yes
Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
 */

/*  leetcode
 Given an unsorted array of integers, find the length of longest continuous increasing subsequence.

Example 1:

Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.

Example 2:

Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.

Note: Length of the array will not exceed 10,000.
 */