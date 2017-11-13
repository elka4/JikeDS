package DP.DP5;

/*


-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1 | w≥Ai-1 且f[i-1][w-Ai-1] ≠-1}

f[i][w]:                用前i个物品拼出重量 w时最大总价值
f[i-1][w]:              用前i-1个物品拼出 重量w时最大总价值
f[i-1][w-Ai-1] + Vi-1:  用前i-1个物品拼出重量w-Ai-1

• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
• f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1 | w≥Ai-1 且f[i-1][w-Ai-1] ≠-1}
• 初始条件:
– f[0][0] = 0: 0个物品可以拼出重量0，最大总价值是0
– f[0][1..M] = -1: 0个物品不能拼出大于0的重量
• 边界情况:
– f[i-1][w-Ai-1]只能在w≥Ai-1，并且f[i-1][w-Ai-1] ≠-1时使用

• 答案:max0<=j<=M{f[N][j] | f[N][j] ≠-1}
• 时间复杂度(计算步数):O(MN)，空间复杂度(数组大小):优化后 可以达到O(M)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

/*
Backpack II:
http://www.lintcode.com/en/problem/backpack-ii/#

题中加入了value元素，基本没有变化。dp[i][j] 为前i个，组成size <= j的最大value值，所以通项改为
dp[i][j] = max(dp[i-1][j] + dp[i-1][j-A[i]] + V[i])

int backPackII(int m, vector<int> A, vector<int> V) {
        // write your code here
        if(m <= 0 || A.empty() || A.size() != V.size()) return 0;
        int n = A.size();
        vector<vector<int>> dp(n+1, vector<int>(m+1, 0));
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(j < A[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = max(dp[i-1][j], dp[i-1][j-A[i-1]] + V[i-1]);
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


//Backpack II
public class _1Backpack_II {
    // 9Ch DP
    public int backPackII(int m, int[] A, int V[]) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[m + 1];
        int i, w;
        f[0] = 0;
        for (i = 1; i <= m; i++) {
            f[i] = -1;
        }

        for (i = 1; i <= n; i++) {

            for (w = m; w >= 0; --w) {

                if (w >= A[i - 1] && f[w - A[i - 1]] != -1) {
                    f[w] = Math.max(f[w], f[w - A[i - 1]] + V[i - 1]);
                }
            }
        }

        int res = 0;
        for (i = 0; i <= m; i++) {
            if (f[i] != -1) {
                res = Math.max(res, f[i]);
            }
        }
        return  res;
    }

////////////////////////////////////////////////////////////////////////////
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    //设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
    public int backPackII1(int m, int[] A, int V[]) {
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
////////////////////////////////////////////////////////////////////////////
    // 方法二
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     */
    public int backPackII2(int m, int[] A, int V[]) {
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
////////////////////////////////////////////////////////////////////////////
}

/*
给定N个物品，重量分别为正整数A0, A1, ..., AN-1，价值分别为正整数V0, V1, ..., VN-1

• 一个背包最大承重是正整数M
• 最多能带走多大价值的物品


给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？

 注意事项

A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的m。

您在真实的面试中是否遇到过这个题？ Yes
样例

挑战对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
O(n x m) memory is acceptable, can you do it in O(m) memory?
 */
