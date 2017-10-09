package DP.DP3;

//_2HouseRobber   18:58
//• 有状态的序列型动态规划

/*
• 设f[i][0]为不偷房子i-1的前提下，前i栋房子中最多能偷多少金币
• 设f[i][1]为偷房子i-1的前提下，前i栋房子中最多能偷多少金币

f[i][0] = max{f[i-1][0], f[i-1][1]}
因为不偷房子i-1，所以房子i-2可以选择偷或不偷

f[i][1] = f[i-1][0] + A[i-1]
偷房子i-1，房子i-2必须不偷


• 设f[i]为窃贼在前i栋房子最多偷多少金币
• f[i] = max{f[i-1], f[i-2] + A[i-1]}
• 初始条件:
– f[0] = 0 (没有房子，偷0枚金币)
– f[1] = A[0]
– f[2] = max{A[0], A[1]}

• 初始化f[0]
• 计算f[1], f[2], ..., f[n]
• 答案是f[n]
• 时间复杂度O(N),空间复杂度O(1)
 */


//House Robber
public class _2HouseRobber {
    // 9Ch DP
    public long houseRobber(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        long[] f = new long[n + 1];
        f[0] = 0;
        f[1] = A[0];
        for (int i = 2; i <= n; i++) {
            f[i] = Math.max(f[i - 1], f[i - 2] + A[i - 1]);
        }
        return f[n];
    }


////////////////////////////////////////////////////////////////////
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    //---方法一---
    public long houseRobber1(int[] A) {
        // write your code here
        int n = A.length;
        if(n == 0)
            return 0;
        long []res = new long[n+1];


        res[0] = 0;
        res[1] = A[0];
        for(int i = 2; i <= n; i++) {
            res[i] = Math.max(res[i-1], res[i-2] + A[i-1]);
        }
        return res[n];
    }

///////////////////////////////////////////////////////////////////////

    //---方法二---
    public long houseRobber2(int[] A) {
        // write your code here
        int n = A.length;
        if(n == 0)
            return 0;
        long []res = new long[2];


        res[0] = 0;
        res[1] = A[0];
        for(int i = 2; i <= n; i++) {
            res[i%2] = Math.max(res[(i-1)%2], res[(i-2)%2] + A[i-1]);
        }
        return res[n%2];
    }

///////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////



}
/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Have you met this question in a real interview? Yes
Example
Given [3, 8, 4], return 8.
 */
