package S_5_Dynamic_Problem_I.all;

/** 394 Coins in a Line


 * Created by tianhuizhu on 6/28/17.
 */
public class _394_Coins_in_a_Line {

    // 方法一
    public class Solution1 {
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

    // 方法二
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
}
