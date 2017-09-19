package S_5_DP1;
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
}
