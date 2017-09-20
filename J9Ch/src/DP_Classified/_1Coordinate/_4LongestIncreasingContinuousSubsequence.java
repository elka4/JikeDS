package DP_Classified._1Coordinate;


/*
• 题意:
• 给定a[0], ..., a[n-1]
• 找到最长的连续子序列i, i+1, i+2, ..., j, 使得a[i]<a[i+1]<...<a[j]，或者 a[i]>a[i+1]>...>a[j]，输出长度j-i+1
• 例子:
• 输入:[5, 1, 2, 3, 4]
• 输出:4 (子序列1, 2, 3, 4)
 */

/*
• 状态:设f[j] =以a[j]结尾的最长连续上升子序列的长度

• f[j] =以a[j]结尾的最长连续上升子序列的长度
f[j] = max{ 1, f[j–1]+1| j>0 and a[j-1] < a[j]}

f[j]
以a[j]结尾的最长连续 上升子序列的长度

1
情况1:子序列 就是a[j]本身

f[j–1]+1| j>0 and a[j-1] < a[j]
情况2:以a[j-1]结尾的最长连续 上升子序列的长度，加上a[j]

 情况2必须满足:
– j>0, 即a[j]前面至少还有一个元素 – a[j] > a[j-1], 满足单调性
• 初始条件:空
 */

//Longest Increasing Continuous Subsequence
public class _4LongestIncreasingContinuousSubsequence {

    public int longestIncreasingContinuousSubsequence(int[] A) {
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
            f[i] = 1;                       //init f[i]
            if (i > 0 && A[i-1] < A[i]) {   //update f[i]
                f[i] = f[i-1] + 1;
            }
            if (f[i] > res) {               //update res
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