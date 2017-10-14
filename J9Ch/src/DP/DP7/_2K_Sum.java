package DP.DP7;

/*
-----------------------------------------------------------------------------------------------
LintCode 89 K Sum

• 题意:
• 给定数组A，包含n个互不相等的正整数
• 问有多少种方式从中找出K个数，使得它们的和是Target
• 例子:
• 输入:A=[1, 2, 3, 4], K=2, Target = 5
• 输出:2 (1+4=5,2+3=5)
-----------------------------------------------------------------------------------------------
题目分析

• 要求从一些正整数中选出一些，使得和是Target
• 背包问题
• 数组A:各个物品的重量
• Target:背包最大称重
• 使得和是Target:背包正好装满
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:最后一个数An-1是否选入这K个数
• 情况一(An-1不选入):需要在前n-1个数中选K个数，使得它们的和是Target
• 情况二(An-1选入):需要在前n-1个数中选K-1个数，使得它们的和是Target - An-1
• 要知道还有几个数可选，以及它们的和需要是多少:序列加状态
• 状态:f[i][k][s]表示有多少种方法可以在前i个数中选出k个，使得它们 的和是s
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• f[i][k][s]表示有多少种方法可以在前i个数中选出k个，使得它们的和是s
f[i][k][s] = f[i-1][k][s] + f[i-1][k-1][s-Ai-1]|s>=Ai-1

f[i][k][s]          有多少种方法可以在前i 个数中选出k个，使得它 们的和是s
f[i-1][k][s]        有多少种方法可以在前 i-1个数中选出k个，使 得它们的和是s
f[i-1][k-1][s-Ai-1] 有多少种方法可以 在前i-1个数中选出k- 1个，使得它们的和 是s-Ai-1
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• f[i][k][s]表示有多少种方法可以在前i个数中选出k个，使得它们的和是s
• f[i][k][s] = f[i-1][k][s] + f[i-1][k-1][s-Ai-1]|s>=Ai-1
• 初始条件:
    – f[0][0][0] = 1
    – f[0][0][s] = 0, s = 1, 2, ..., Target
• 边界条件:
    – 如果s<Ai-1,只考虑情况一f[i-1][k][s]
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[0][0~K][0~Target] • f[1][0~K][0~Target] •...
• f[N][0~K][0~Target]
• 答案是f[N][K][Target]
• 时间复杂度O(N*K*Target)，空间复杂度O(N*K*Target)，可以用滚动数 组优化至O(K*Target)

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */

import org.junit.Test;

//K Sum
public class _2K_Sum {
    // 9CH DP
    public int  kSum(int A[], int K, int target) {
        int n = A.length;
        int old, now = 0;
        int[][][] f = new int[2][K + 1][target + 1];
        int i, k, s;
        //init
        for (k = 0; k <= K; k++) {
            for (s = 0; s <= target; s++) {
                f[now][k][s] = 0;
            }
        }

        f[now][0][0] = 1;

        for (i = 1; i <= n; i++) {
            old = now;
            now = 1 - now;
            for (k = 0; k <= K; k++) {
                for (s = 0; s <= target; s++) {
                    // do not select A[i-1]
                    f[now][k][s] = f[old][k][s];

                    // select A[i-1]
                    if (k >= 1 && s >= A[i - 1]) {
                        f[now][k][s] += f[old][k - 1][s - A[i - 1]];
                    }
                }
            }
        }

        return f[now][K][target];
    }

    //给出[1,2,3,4]，k=2， target=5，[1,4] and [2,3]是2个符合要求的方案

    @Test
    public void test01() {
        int A[] = {1,2,3,4};
        int K = 2;
        int target = 5;
        System.out.println(kSum(A, K, 5));
    }


///////////////////////////////////////////////////////////////////////
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int  kSum2(int A[], int k, int target) {
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

    @Test
    public void test02() {
        int A[] = {1,2,3,4};
        int K = 2;
        int target = 5;
        System.out.println(kSum2(A, K, 5));
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

/*
给定n个不同的正整数，整数k（k < = n）以及一个目标数字。　

在这n个数里面找出K个数，使得这K个数的和等于目标数字，求问有多少种方案？

您在真实的面试中是否遇到过这个题？ Yes
样例
给出[1,2,3,4]，k=2， target=5，[1,4] and [2,3]是2个符合要求的方案
 */