package DP.DP5;


/*

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


// Coins In A Line II
public class _7CoinsInALineII {

//////////////////////////////////////////////////////////////////////////////////////////////
   /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int []dp = new int[values.length + 1];
        boolean []flag =new boolean[values.length + 1];
        int sum = 0;
        for(int now : values)
            sum += now;

        return sum < 2*MemorySearch(values.length, dp, flag, values);
    }
    int MemorySearch(int n, int []dp, boolean []flag, int []values) {
        if(flag[n] == true)
            return dp[n];
        flag[n] = true;
        if(n == 0)  {
            dp[n] = 0;
        } else if(n == 1) {
            dp[n] = values[values.length-1];
        } else if(n == 2) {
            dp[n] = values[values.length-1] + values[values.length-2];
        } else if(n == 3){
            dp[n] = values[values.length-2] + values[values.length-3];
        } else {
            dp[n] = Math.max(
                    Math.min(MemorySearch(n-2, dp, flag,values) ,
                            MemorySearch(n-3, dp, flag, values)) + values[values.length-n],
                    Math.min(MemorySearch(n-3, dp, flag, values),
                            MemorySearch(n-4, dp, flag, values)) + values[values.length-n] +
                            values[values.length - n + 1]
            );
        }

        return dp[n];
    }
//////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////
}
/*
有 n 个不同价值的硬币排成一条线。两个参赛者轮流从左边依次拿走 1 或 2 个硬币，直到没有硬币为止。计算两个人分别拿到的硬币总价值，价值高的人获胜。

请判定 第一个玩家 是输还是赢？

您在真实的面试中是否遇到过这个题？ Yes
样例
给定数组 A = [1,2,2], 返回 true.

给定数组 A = [1,2,4], 返回 false.

标签
 */