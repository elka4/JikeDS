package DP.DP5;


/*
Backpack III:
http://www.lintcode.com/en/problem/backpack-iii/#

Backpack III 是无限背包，表示每一个item可以取无数次。
所以通项定义稍有变化。dp[i][j] 为当前到第i个item，拿到size <= j 的最大价值。所以有最后不取第i个item，dp[i][j] = dp[i-1][j], 取第i个item，由于之前也可以取item i，dp[i][j] = dp[i][j-A[i]] + A[i]

通项公式为：
dp[i][j] = max(dp[i-1][j] + dp[i][j-A[i]] + A[i])

int backPackIII(vector<int>& A, vector<int>& V, int m) {
 // Write your code here
    if(m <= 0 || A.empty() || A.size() != V.size()) return 0;
    int n = A.size();
    vector<vector<int>> dp(n+1, vector<int>(m+1, 0));
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(j < A[i-1]){
                dp[i][j] = dp[i-1][j];
            }else{
                dp[i][j] = max(dp[i-1][j], dp[i][j-A[i-1]] + V[i-1]);
            }
        }
    }
    return dp[n][m];
 }

作者：stepsma
链接：http://www.jianshu.com/p/7f192e75d734
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

//Backpack III
public class _2BackpackIII {
    public class Solution {
        /**
         * @param A an integer array
         * @param V an integer array
         * @param m an integer
         * @return an array
         */
        public int backPackIII(int[] A, int[] V, int m) {
            // Write your code here
            int n = A.length;
            int[] f = new int[m+1];
            for (int i = 0; i < n; ++i)
                for (int j = A[i]; j <= m; ++j)
                    if (f[j - A[i]] + V[i] > f[j])
                        f[j] = f[j - A[i]] + V[i];
            return f[m];
        }
    }

    // 2D version, 如果你无法理解一维的solution, 可以从二维的solution入手,然后思考空间的优化
    public class Solution2 {
        /**
         * @param A an integer array
         * @param V an integer array
         * @param m an integer
         * @return an array
         */
        public int backPackIII(int[] A, int[] V, int m) {
            // Write your code here
            int n = A.length;
            int[][] f = new int[n + 1][m + 1];
            for (int i = 1; i <= n; ++i)
                for (int j = 0; j <= m; ++j) {
                    f[i][j] = f[i - 1][j];
                    if (j >= A[i - 1])
                        f[i][j] = Math.max(f[i][j - A[i - 1]] + V[i - 1], f[i][j]);
                }
            return f[n][m];
        }
    }
////////////////////////////////////////////////////////////////////////
}
/*

 */
