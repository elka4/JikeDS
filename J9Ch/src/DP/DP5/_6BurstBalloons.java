package DP.DP5;

//• 区间型动态规划


/*
• 设f[i][j]为扎破i+1~j-1号气球，最多获得的金币数 – i和j不能扎破
f[i][j] = maxi<k<j{f[i][k] + f[k][j] + a[i] * a[k] * a[j]}

f[i][j]:            扎破i+1~j-1号气球 最多获得的金币数
f[i][k]:            扎破i+1~k-1号气球 最多获得的金币数
f[k][j]:            扎破k+1~j-1号气球 最多获得的金币数
a[i] * a[k] * a[j]: 最后扎破k号气球 获得的金币数


• 初始条件:f[0][1] = f[1][2] = ... = f[N][N+1] = 0
– 当没有气球要扎破时，最多获得0枚金币
-----------------------------------------------------------------------------------------------
*/


//  312. Burst Balloons
//  https://leetcode.com/problems/burst-balloons/
//
public class _6BurstBalloons {
//------------------------------------------------------------------------------
    //1
    // 9CH DP
    public int maxCoins(int[] B) {
        int n = B.length;
        if (n == 0) {
            return 0;
        }

        int i, j, k, len;
        int[] A = new int[n + 2];
        A[0] = A[n + 1] = 1;
        for (i = 1; i <= n; i++) {
            A[i] = B[i - 1];
        }

        // A[0] = 1, A[1], ...., A[n], A[n + 1] = 1
        int[][] f = new int[n + 2][n + 2];
        for (i = 0; i < n + 1; i++) {
            f[i][i + 1] = 0;
        }

        for (len = 3; len <= n + 2; len++) {
            for (i = 0; i <= n - len + 2; i++) {
                j = i + len - 1;
                f[i][j] = 0;
                // k is the index of last balloon burst
                for (k = i + 1; k < j; k++) {
                    f[i][j] = Math.max(f[i][j], f[i][k] + f[k][j] + A[i] * A[k] * A[j]);
                }
            }
        }
        return f[0][n + 1];
    }

//------------------------------------------------------------------------------
    //2
    // 9Ch
    //记忆化
    public int maxCoins1(int[] nums) {
        int n = nums.length;
        int [][]dp = new int [n+2][n+2];
        int [][]visit = new int[n+2][n+2];
        int [] arr = new int [n+2];
        for (int i = 1; i <= n; i++){
            arr[i] = nums[i-1];
        }
        arr[0] = 1;
        arr[n+1] = 1;

        return search(arr, dp, visit, 1 , n);
    }

    public int search(int []arr, int [][]dp, int [][]visit, int left, int right) {
        if(visit[left][right] == 1)
            return dp[left][right];

        int res = 0;
        for (int k = left; k <= right; ++k) {
            int midValue =  arr[left - 1] * arr[k] * arr[right + 1];
            int leftValue = search(arr, dp, visit, left, k - 1);
            int rightValue = search(arr, dp, visit, k + 1, right);
            res = Math.max(res, leftValue + midValue + rightValue);
        }
        visit[left][right] = 1;
        dp[left][right] = res;
        return res;
    }

//------------------------------------------------------------------------------
}
/*
有n个气球，编号为0到n-1，每个气球都有一个分数，存在nums数组中。每次吹气球i可以得到的分数为 nums[left] * nums[i] * nums[right]，left和right分别表示i气球相邻的两个气球。当i气球被吹爆后，其左右两气球即为相邻。要求吹爆所有气球，得到最多的分数。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 [4, 1, 5, 10]
返回 270

nums = [4, 1, 5, 10] burst 1, 得分 4 * 1 * 5 = 20
nums = [4, 5, 10]    burst 5, 得分 4 * 5 * 10 = 200
nums = [4, 10]       burst 4, 得分 1 * 4 * 10 = 40
nums = [10]          burst 10, 得分 1 * 10 * 1 = 10
总共的分数为 20 + 200 + 40 + 10 = 270
 */