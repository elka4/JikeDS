package DP._s_6_DP2;

public class _7k_Sum {
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
}
/*
k数和

 描述
 笔记
 数据
 评测
给定n个不同的正整数，整数k（k < = n）以及一个目标数字。　

在这n个数里面找出K个数，使得这K个数的和等于目标数字，求问有多少种方案？

您在真实的面试中是否遇到过这个题？ Yes
样例
给出[1,2,3,4]，k=2， target=5，[1,4] and [2,3]是2个符合要求的方案
 */