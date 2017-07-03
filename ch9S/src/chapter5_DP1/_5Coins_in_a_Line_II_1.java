package ch9S.chapter5_DP1;

public class _5Coins_in_a_Line_II_1 {
/**
 * @param values: an array of integers
 * @return: a boolean which equals to true if the first player will win
 */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n = values.length;
        int []dp = new int[n + 1]; //dp[i]现在还剩i个硬币，现在当前取硬币的人最后最多取硬币价值
        boolean []flag =new boolean[n + 1];
        int []sum = new int[n+1];//sum[i]是最后i个硬币的总和
        int allsum = values[n-1];
        sum[n-1] = values[n-1];
        //预处理
        for(int i = n-2; i >= 0; i--) {
            sum[i] += sum[i+1] + values[i];
            allsum += values[i];
        }
        return allsum/2 < MemorySearch(0, n, dp, flag, values, sum);
    }
    int MemorySearch(int i, int n, int []dp, boolean []flag, int []values, int []sum) {
        if(flag[i] == true)
            return dp[i];
        flag[i] = true;
        if(i == n)  {
            dp[n] = 0;
        } else if(i == n-1) {
            dp[i] = values[i];
        } else if(i == n-2) {
            dp[i] = values[i] + values[i + 1];
        } else {
            dp[i] = sum[i] -
                Math.min(MemorySearch(i+1, n, dp, flag, values, sum) ,
                        MemorySearch(i+2, n, dp, flag, values, sum));
        }
        return dp[i];
    }
}
