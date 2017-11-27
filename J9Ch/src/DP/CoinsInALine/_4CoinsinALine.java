package DP.CoinsInALine;

//• 博弈型
//感觉有点像坐标型
/*
•  f[i]表示面对i个石子，是否限售必胜(f[i] = TRUE / FALSE)


f[i] = f[i-1] == FALSE OR f[i-2] == FALSE

-----------------------------------------------------------------------------------------------
*/

//  Coins in A Line
//  http://lintcode.com/zh-cn/problem/coins-in-a-line/
//  5:
public class _4CoinsinALine {
//------------------------------------------------------------------------------
    //1
    // 9Ch DP
    public boolean firstWillWin(int n) {
        if (n == 0){
            return false;
        }

        if (n <= 2) {
            return  true;
        }

        boolean[] f = new boolean[n + 1];
        f[0] = false;
        f[1] = f[2] = true;
        for (int i = 3; i <= n ; i++) {
            /*
            f[i - 1] == false 对手上一步拿1个石头必败
            f[i - 2] == false 对手上一步拿2个石头必败
            以上情况下我必胜

             */
            f[i] = (f[i - 1] == false) || (f[i - 2] == false);
        }
        return f[n];

//        return n % 3 != 0;

    }


//-------------------------------------------------------------------------------
    //2
    // 9Ch DP 更好理解的版本
    public boolean firstWillWin2(int n) {
        if (n == 0){
            return false;
        }

        if (n <= 2) {
            return  true;
        }

        boolean[] f = new boolean[n + 1];
        f[0] = false;
        f[1] = f[2] = true;
        for (int i = 3; i <= n ; i++) {

            if (f[i - 1] == false ||f[i - 2] == false){
                f[i] = true;
            };
        }
        return f[n];

//        return n % 3 != 0;

    }

//------------------------------------------------------------------------------
    //3
    // 9Ch
    // 方法一
    public class Solution {
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
    }

//------------------------------------------------------------------------------
    //4
    // 9Ch
    // 方法二 StackOverflow
    public class Solution2 {
        /**
         * @param n: an integer
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int n) {
            // write your code here
            boolean []dp = new boolean[n+1];
            boolean []flag = new boolean[n+1];
            return MemorySearch(n, dp, flag);
        }
        boolean MemorySearch(int i, boolean []dp, boolean []flag) {
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
                dp[i] = !MemorySearch(i-1, dp, flag) || !MemorySearch(i-2, dp, flag);
            }
            flag[i] =true;
            return dp[i];
        }
    }

//------------------------------------------------------------------------------
    //5
    // 9Ch
    //方法三
    public class Solution3 {
        /**
         * @param n: an integer
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int n) {
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
    }

//------------------------------------------------------------------------------
}
/*
有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。拿到最后一枚硬币的人获胜。

请判定 第一个玩家 是输还是赢？

您在真实的面试中是否遇到过这个题？ Yes
样例
n = 1, 返回 true.

n = 2, 返回 true.

n = 3, 返回 false.

n = 4, 返回 true.

n = 5, 返回 true.

挑战
O(1) 时间复杂度且O(1) 存储。
 */

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

