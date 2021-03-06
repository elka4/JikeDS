package DP_Classified._7Gamble;

//Coins in A Line
public class _4CoinsinALine {
    // 方法一
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        int []dp = new int[n+1];

        return MemorySearch(n, dp);

    }
    boolean MemorySearch(int n, int []dp) { // 0 is empty, 1 is false, 2 is true
        if(dp[n] != 0) {
            if(dp[n] == 1)
                return false;
            else
                return true;
        }
        if(n <= 0) {
            dp[n] = 1;
        } else if(n == 1) {
            dp[n] = 2;
        } else if(n == 2) {
            dp[n] = 2;
        } else if(n == 3) {
            dp[n] = 1;
        } else {
            if((MemorySearch(n-2, dp) && MemorySearch(n-3, dp)) ||
                    (MemorySearch(n-3, dp) && MemorySearch(n-4, dp) )) {
                dp[n] = 2;
            } else {
                dp[n] = 1;
            }
        }
        if(dp[n] == 2)
            return true;
        return false;
    }

//------------------------------------------------------------------------------

    // 方法二 StackOverflow

    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin2(int n) {
        // write your code here
        boolean []dp = new boolean[n+1];
        boolean []flag = new boolean[n+1];
        return MemorySearch2(n, dp, flag);
    }
    boolean MemorySearch2(int i, boolean []dp, boolean []flag) {
        if(flag[i] == true) {
            return dp[i];
        }
        if(i == 0) {
            dp[i] = false;
        } else if(i == 1) {
            dp[i] = true;
        } else if(i == 2) {
            dp[i] = true;
        } else {
            dp[i] = !MemorySearch2(i-1, dp, flag) || !MemorySearch2(i-2, dp, flag);
        }
        flag[i] =true;
        return dp[i];
    }

//------------------------------------------------------------------------------

    //方法三
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin3(int n) {
        // write your code here
        if (n == 0)
            return false;
        else if (n == 1)
            return true;
        else if (n == 2)
            return true;

        boolean []dp = new boolean[n+1];
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        for (int i = 3; i <= n; i++)
            dp[i] = !dp[i - 1] || !dp[i - 2];

        return dp[n];
    }


//------------------------------------------------------------------------------

}
/*
There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?

Have you met this question in a real interview? Yes
Example
n = 1, return true.

n = 2, return true.

n = 3, return false.

n = 4, return true.

n = 5, return true.


 */
