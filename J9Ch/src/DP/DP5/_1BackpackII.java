package DP.DP5;

public class _1BackpackII {
    public class Solution {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A & V: Given n items with size A[i] and value V[i]
         * @return: The maximum value
         */

        public int backPackII(int m, int[] A, int V[]) {
            // write your code here
            int[][] dp = new int[A.length + 1][m + 1];
            for(int i = 0; i <= A.length; i++){
                for(int j = 0; j <= m; j++){
                    if(i == 0 || j == 0){
                        dp[i][j] = 0;
                    }
                    else if(A[i-1] > j){
                        dp[i][j] = dp[(i-1)][j];
                    }
                    else{
                        dp[i][j] = Math.max(dp[(i-1)][j], dp[(i-1)][j-A[i-1]] + V[i-1]);
                    }
                }
            }
            return dp[A.length][m];
        }
    }

    // 方法二
    public class Solution2 {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A & V: Given n items with size A[i] and value V[i]
         */
        public int backPackII(int m, int[] A, int V[]) {
            // write your code here
            int[] f = new int[m+1];
            for (int i = 0; i <=m ; ++i) f[i] = 0;
            int n = A.length , i, j;
            for(i = 0; i < n; i++){
                for(j = m; j >= A[i]; j--){
                    if (f[j] < f[j - A[i]] + V[i])
                        f[j] = f[j - A[i]] + V[i];
                }
            }
            return f[m];
        }
    }

}

/*
给定N个物品，重量分别为正整数A0, A1, ..., AN-1，价值分别为正整数V0,
V1, ..., VN-1
• 一个背包最大承重是正整数M
• 最多能带走多大价值的物品
 */
