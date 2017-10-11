package DP.DP5;





/*
• 和上一题唯一的不同是:每种物品都有无穷多个
• 一个背包最大承重是M
• BackpackII的转移方程
• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)

f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1}
f[i][w]:                用前i个物品拼出重量 w时最大总价值
f[i-1][w]:              用前i-1个物品拼出 重量w时最大总价值
f[i-1][w-Ai-1] + Vi-1:  用前i-1个物品拼出重量w-Ai-1 时最大总价值，加上第i个物品

• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)



-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */




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

import StdLib.In;

//Backpack III
public class _2BackpackIII {
    public int backPackIII(int[] A, int[] V, int m) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[m + 1];
        int i, j;
        for (i = 0; i <= m; i++) {
            f[i] = Integer.MIN_VALUE;
        }

        f[0] = 0;
        for (i = 0; i < n; i++) {
            // f[0] f[1]
            for (j = 0; j <= m - A[i]; j++) {
                // updated f[w]: slides f[i][w]
                // original f[w]: slides f[i-1][w]
                // max{f[i - 1][w], f[i-1][w-Ai-1] + Vi-1 | w >= Ai-1 且f[i-1][w-Ai-1] != -1}

                if (f[j] != Integer.MIN_VALUE) {
                    f[j + A[j]] = Math.max(f[j + A[i]], f[j] + V[i]);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (i = 0; i <= m; i++) {
            res = Math.max(res, f[i]);
        }
        return  res;
    }

////////////////////////////////////////////////////////////////////////
    /**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    public int backPackIII1(int[] A, int[] V, int m) {
        // Write your code here
        int n = A.length;
        int[] f = new int[m+1];
        for (int i = 0; i < n; ++i)
            for (int j = A[i]; j <= m; ++j)
                if (f[j - A[i]] + V[i] > f[j])
                    f[j] = f[j - A[i]] + V[i];
        return f[m];
    }
////////////////////////////////////////////////////////////////////////
    // 2D version, 如果你无法理解一维的solution, 可以从二维的solution入手,然后思考空间的优化
    /**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    public int backPackIII2(int[] A, int[] V, int m) {
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
////////////////////////////////////////////////////////////////////////
}
/*

 */
