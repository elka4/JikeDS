package DP.DP7;

/*
• f[i][k][s]表示有多少种方法可以在前i个数中选出k个，使得它们的和是s
f[i][k][s] = f[i-1][k][s] + f[i-1][k-1][s-Ai-1]|s>=Ai-1

f[i][k][s]          有多少种方法可以在前i 个数中选出k个，使得它 们的和是s
f[i-1][k][s]        有多少种方法可以在前 i-1个数中选出k个，使 得它们的和是s
f[i-1][k-1][s-Ai-1] 有多少种方法可以 在前i-1个数中选出k- 1个，使得它们的和 是s-Ai-1

• f[i][k][s]表示有多少种方法可以在前i个数中选出k个，使得它们的和是s
• f[i][k][s] = f[i-1][k][s] + f[i-1][k-1][s-Ai-1]|s>=Ai-1
• 初始条件:
– f[0][0][0] = 1
– f[0][0][s] = 0, s = 1, 2, ..., Target
• 边界条件:
– 如果s<Ai-1,只考虑情况一f[i-1][k][s]
 */

//K Sum
public class _5K_Sum {
///////////////////////////////////////////////////////////////////////
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int  kSum(int A[], int k, int target) {
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t];
                } // for t
            } // for j
        } // for i
        return f[n][k][target];
    }

///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

}
/*
Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.
 */
