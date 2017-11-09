package DP.DP5;

//• 区间型动态规划


/*
• 设f[i][j]为一方在面对a[i..j]这些数字时，能得到的最大的与对手的数字差

f[i][j] = max{a[i] - f[i+1][j], a[j] - f[i][j-1]}
f[i][j]:            为一方在面对a[i..j]时，能得到的最大的与对手的数字差
a[i] - f[i+1][j]:   选择a[i]，对手采取最优策略时自己能得到的最大的 与对手的数字差
a[j] - f[i][j-1]:   选择a[j]，对手采取最优策略时自己能得到的最 大的与对手的数字差

• 设f[i][j]为一方在面对a[i..j]这些数字时，能得到的最大的与对手的数字差
• f[i][j] = max{a[i]-f[i+1][j], a[j]-f[i][j-1]}
• 只有一个数字a[i]时，己方得a[i]分，对手0分，数字差为a[i] – f[i][i] = a[i] (i=0, 1, ..., N-1)


-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


import org.junit.Test;

//  Coins In A Line III
public class _4CoinsInALineIII {

    // 9CH DP
    public boolean firstWillWin (int[] A) {
        int n = A.length;
        if (n == 0) {
            return true;
        }

        int[][] f = new int[n][n];
        int i, j, len;
        for (i = 0; i < n; i++) {
            f[i][i] = A[i];
        }

        for (len = 2; len <= n; len++) {
            for (i = 0; i <= n - len; i++) {
                j = i + len - 1;
                f[i][j] = Math.max(A[i] - f[i + 1][j], A[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] >= 0;
    }

    @Test
    public void test01() {
        /*
        Given array A = [3,2,2], return true.
        Given array A = [1,2,4], return true.
        Given array A = [1,20,4], return false.
         */
        System.out.println(firstWillWin(new int[]{3,2,2}));
        System.out.println(firstWillWin(new int[]{1,2,4}));
        System.out.println(firstWillWin(new int[]{1,20,4}));
    }
////////////////////////////////////////////////////////////////////
    // Linpz verision
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin1(int[] values) {
        // write your code here

        int n = values.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; ++i)
            sum[i] = sum[i - 1] + values[i - 1];

        // s[i][j] = sum[j + 1] -  sum[i];

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i)
            dp[i][i] = values[i];

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i < n; ++i) {
                int j = i + len - 1;
                if (j >= n)
                    continue;
                int s = sum[j + 1] - sum[i];
                dp[i][j] = Math.max(s - dp[i + 1][j], s - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1]  > sum[n] / 2;
    }

////////////////////////////////////////////////////////////////////

    // 方法一
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin2(int[] values) {
        // write your code here
        int n = values.length;
        int [][]dp = new int[n + 1][n + 1];
        boolean [][]flag =new boolean[n + 1][n + 1];

        int sum = 0;
        for(int now : values)
            sum += now;

        return sum < 2*MemorySearch(0,values.length - 1, dp, flag, values);
    }

    int MemorySearch(int left, int right, int [][]dp, boolean [][]flag, int []values) {
        if(flag[left][right])
            return dp[left][right];
        flag[left][right] = true;
        if(left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int  pick_left = Math.min(MemorySearch(left + 2, right, dp, flag, values),
                    MemorySearch(left + 1, right - 1, dp, flag, values)) + values[left];

            int  pick_right = Math.min(MemorySearch(left, right - 2, dp, flag, values),
                        MemorySearch(left + 1, right - 1, dp, flag, values)) + values[right];
            dp[left][right] = Math.max(pick_left, pick_right);
        }
        return dp[left][right];
    }

////////////////////////////////////////////////////////////////////

    // 方法二

    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin3(int[] values) {
        // write your code here
        int n = values.length;
        int [][]dp = new int[n + 1][n + 1];
        boolean [][]flag =new boolean[n + 1][n + 1];
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum[i][j] = i == j ? values[j] : sum[i][j-1] + values[j];
            }
        }
        int allsum = 0;
        for(int now : values)
            allsum += now;

        return allsum < 2*MemorySearch3(0,values.length - 1, dp, flag, values, sum);
    }
    int MemorySearch3(int left, int right, int [][]dp, boolean [][]flag, int []values, int [][]sum) {
        if(flag[left][right])
            return dp[left][right];

        flag[left][right] = true;
        if(left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int cur = Math.min(MemorySearch3(left+1, right, dp, flag, values, sum),
                    MemorySearch3(left,right-1, dp, flag, values, sum));
            dp[left][right] = sum[left][right] - cur;
        }
        return dp[left][right];
    }
////////////////////////////////////////////////////////////////////
}
/*
有 n 个硬币排成一条线，每一枚硬币有不同的价值。两个参赛者轮流从任意一边取一枚硬币，知道没有硬币为止。计算拿到的硬币总价值，价值最高的获胜。

请判定 第一个玩家 是输还是赢？
 */

/*
There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.
Could you please decide the first player will win or lose?
Example
Given array A = [3,2,2], return true.
Given array A = [1,2,4], return true.
Given array A = [1,20,4], return false.
Challenge
Follow Up Question:
If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?
 */