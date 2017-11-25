package DP._s_5_DP1;


/*
• State:
    • dp[i][j] 现在还第i到第j的硬币，现在当前取硬币的人最后最多取硬币价值
• Function:
    • sum[i][j]第i到第j的硬币价值总和
    • dp[i][j] = sum[i][j] - min(dp[i+1][j], dp[i][j-1]);
• Intialize:
    • dp[i][i] = coin[i],
• Answer:
    • dp[0][n-1]
 */


public class _6Coins_in_a_Line_III_1 {
	/**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
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
        
        return allsum < 2*MemorySearch(0,values.length - 1, dp, flag, values, sum);
    }

    int MemorySearch(int left, int right, int [][]dp, boolean [][]flag, int []values, int [][]sum) {
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
            // 对手下一步能拿到的最小值
            int cur = Math.min(MemorySearch(left+1, right, dp, flag, values, sum),
                              MemorySearch(left,right-1, dp, flag, values, sum));
            // 本人当前能拿到的最大值
            dp[left][right] = sum[left][right] - cur;
        }
        return dp[left][right];   
    }

//------------------------------------------------------------------------------

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

        return sum < 2*MemorySearch2(0,values.length - 1, dp, flag, values);
    }
    int MemorySearch2(int left, int right, int [][]dp, boolean [][]flag, int []values) {

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
            int  pick_left = Math.min(MemorySearch2(left + 2, right, dp, flag, values),
                    MemorySearch2(left + 1, right - 1, dp, flag, values)) + values[left];

            int  pick_right = Math.min(MemorySearch2(left, right - 2, dp, flag, values),
                    MemorySearch2(left + 1, right - 1, dp, flag, values)) + values[right];

            dp[left][right] = Math.max(pick_left, pick_right);
        }
        return dp[left][right];
    }
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
}
