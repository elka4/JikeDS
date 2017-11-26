package DP._s_5_DP1;
//方法一
@SuppressWarnings("all")
public class _4Coins_in_a_Line_1 {
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
            dp[i] = !MemorySearch(i-1, dp, flag) || !MemorySearch(i-2, dp, flag);//状态转移
            //i-1 i-2是指对手走了前一步或者前两步，他赢我输，所以取F非 "！"
        }
        flag[i] =true;
        return dp[i];
    }

//-------------------------------------------------------------------------------

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
//-------------------------------------------------------------------------------
}
