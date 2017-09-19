package DP.Classified._1Coordinate;

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