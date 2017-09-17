package DP.DP6;

public class _7OnesandZeroes {
    //方法一 未进行空间复杂度优化：
    public class Solution {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
            for (int i = 1; i <= strs.length; i++) {
                int[] cost = count(strs[i - 1]);
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        if (j >= cost[0] && k >= cost[1]) {
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - cost[0]][k - cost[1]] + 1);
                        } else {
                            dp[i][j][k] = dp[i - 1][j][k];
                        }
                    }
                }
            }
            return dp[strs.length][m][n];
        }

        public int[] count(String str) {
            int[] cost = new int[2];
            for (int i = 0; i < str.length(); i++)
                cost[str.charAt(i) - '0']++;
            return cost;
        }
    };
    // 方法二 进行空间复杂度优化：
    public class Solution2 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String s : strs) {
                int[] cost = count(s);
                for (int i = m; i >= cost[0]; i--)
                    for (int j = n; j >= cost[1]; j--)
                        dp[i][j] = Math.max(dp[i][j], dp[i - cost[0]][j - cost[1]] + 1);
            }
            return dp[m][n];
        }

        public int[] count(String str) {
            int[] cost = new int[2];
            for (int i = 0; i < str.length(); i++)
                cost[str.charAt(i) - '0']++;
            return cost;
        }
    }
}
