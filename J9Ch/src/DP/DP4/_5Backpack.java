package DP.DP4;


/*
•  f[i][w] = 能否用前i个物品拼出重量 w (TRUE / FALSE)

f[i][w] = f[i-1][w] OR f[i-1][w-Ai-1]

f[i][w]： 能否用前i个物品拼出重量w
f[i-1][w]： 能否用前i-1个物品拼出重量w
f[i-1][w-Ai-1]：能否用前i-1个物品拼出重量w-Ai-1， 再加上第i个物品

•  初始条件：
– f[0][0] = TRUE:       0个物品可以拼出重量0
– f[0][1..M] = FALSE:   0 个物品不能拼出大于0的重量

•  边界情况：
– f[i-1][w - Ai-1] 只能在 w≥Ai-1 时使用

•  记录前i个物品能拼出哪些重量
•  前i个物品能拼出的重量
- 前i-1个物品能拼出的重量
- 前i-1个物品能拼出的重量 + 第i个物品的重量Ai-1

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


/* 背包问题总结 (Backpack Problem)

设dp[i][j] 为前i个数，取size <= j 的最大值，则有两种可能。
    1. 不拿item i，dp[i][j] = dp[i-1][j];
    2. 拿item i，dp[i][j] = dp[i-1][j-A[i]] + A[i]。所以通项公式为：
dp[i][j] = max(dp[i-1][j] + dp[i-1][j-A[i]] + A[i])

作者：stepsma
链接：http://www.jianshu.com/p/7f192e75d734
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

//Backpack
public class _5Backpack {

    public int backPack1(int m, int[] nums) {
        // write your code here
        int n = nums.length;
        //n是物品个数，m是物品大小。
        //f[i][j]: 能否用前i个物品拼出重量 j (TRUE / FALSE)
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {//j是物品大小

                if (j >= nums[i - 1]) {
                    //f[i - 1][j]： 不放当前，取顶头
                    //f[i - 1][j - A[i - 1]]： 放当前+ A[i - 1]， 取斜上方。。。。
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - nums[i - 1]] + nums[i - 1]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        return f[n][m];
    }

//-------------------------------------------------------------------------////
    // 9Ch DP
    public int backPack2(int m, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        boolean[][] f = new boolean[n + 1][m + 1];
        int i, j;
        f[0][0] = true;
        for (j = 1; j <= m; j++) {
            f[0][j] = false;
        }

        for (i = 1; i <= n; i++) {
            for (j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] = f[i][j] || f[i - 1][j - nums[i - 1]];
                }
            }
        }

        for (i = m; i >= 0; --i) {
            if (f[n][i]) {
                return i;
            }
        }
        return 0;
    }

//-------------------------------------------------------------------------////
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack3(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];

        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = false;
            }
        }

        f[0][0] = true;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1] && f[i-1][j - A[i-1]]) {
                    f[i][j] = true;
                }
            } // for j
        } // for i

        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }

        return 0;
    }
//-------------------------------------------------------------------------////

}

/*
在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]

 注意事项

你不可以将物品进行切割。

您在真实的面试中是否遇到过这个题？ Yes
样例
如果有4个物品[2, 3, 5, 7]

如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。

如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。

函数需要返回最多能装满的空间大小。
 */


/*
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

 Notice

You can not divide any item into small pieces.

Have you met this question in a real interview? Yes
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.
 */
