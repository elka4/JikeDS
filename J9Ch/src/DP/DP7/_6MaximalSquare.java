package DP.DP7;

/*
-----------------------------------------------------------------------------------------------
LintCode 436: Maximal Square

• 题意:
• 给定一个mxn的网格，每个格子里都是0或者1 • 求一块最大的全由1组成的正方形
• 输出面积
-----------------------------------------------------------------------------------------------
直觉

• 枚举左上角，枚举右下角，检查内部是否全是1
• 左上角和右下角各有O(M*N)种可能性，内部大小也是O(M*N)级别
• 时间复杂度O(M*N*M*N*M*N)
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最大的全1正方形，要么是边长为1，要么边长是L>1
• 右下角(i, j)肯定是1

• 以(i-1, j-1)为右下角的最大全1正方形边长至少是L-1

• 以(i, j-1)为右下角的最大全1正方形边长至少是L-1


-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 换个角度想，如果以(i-1,j-1), (i-1, j), (i, j-1)为右下角的最大全1正方形的 边长分别是L1, L2和L3，而(i, j)格子里是1，那么以(i, j)为右下角的最大全 1正方形的边长应该是min{L1,L2,L3} +1

-----------------------------------------------------------------------------------------------
子问题

• 于是，需要求以(i-1,j-1), (i-1, j), (i, j-1)为右下角的最大全1正方形的边长
• 而原来是求以(i, j)为右下角的最大全1正方形的边长
• 子问题
• 状态:设f[i][j] = 以(i, j)为右下角的最大全1正方形的边长
• 坐标型动态规划
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[i][j] = 以(i, j)为右下角的最大全1正方形的边长
f[i][j] = 0， 如果(i, j)格是0
f[i][j] =  min{f[i-1][j], f[i][j-1], f[i-1][j-1]} + 1，如果(i, j)格是1

-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• i=0或者j=0，即最上边一行或最左边一列
f[i][j] = 0，如果(i, j)格是0
f[i][j] = 1，如果(i, j)格是1且i=0或j=0
f[i][j] = min{f[i-1][j], f[i][j-1], f[i-1][j-1]} + 1，如果(i, j)格是1

• 初始条件:空
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[0][0], f[0][1], ..., f[0][n-1]
• f[1][1], f[1][2], ..., f[1][n-1]
•...
• f[m-1][0], f[m-1][1], ..., f[m-1][n-1]
• 答案是maxi,j{f[i][j]2}
• 时间复杂度(计算步数):O(MN)，空间复杂度(数组大小):O(MN)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */

import org.junit.Test;

//  221. Maximal Square
//  https://leetcode.com/problems/maximal-square/description/
//  http://www.lintcode.com/zh-cn/problem/maximal-square/
public class _6MaximalSquare {
    //https://leetcode.com/problems/maximal-square/solution/
    //https://leetcode.com/articles/maximal-square/

    //Approach #1 Brute Force [Accepted]
    public class Solution01 {
        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int maxsqlen = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] == '1') {
                        int sqlen = 1;
                        boolean flag = true;
                        while (sqlen + i < rows && sqlen + j < cols && flag) {
                            for (int k = j; k <= sqlen + j; k++) {
                                if (matrix[i + sqlen][k] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                            for (int k = i; k <= sqlen + i; k++) {
                                if (matrix[k][j + sqlen] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                sqlen++;
                        }
                        if (maxsqlen < sqlen) {
                            maxsqlen = sqlen;
                        }
                    }
                }
            }
            return maxsqlen * maxsqlen;
        }
    }

    //Approach #2 (Dynamic Programming) [Accepted]
    public class Solution02 {
        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int[][] dp = new int[rows + 1][cols + 1];
            int maxsqlen = 0;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    if (matrix[i-1][j-1] == '1'){
                        dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                        maxsqlen = Math.max(maxsqlen, dp[i][j]);
                    }
                }
            }
            return maxsqlen * maxsqlen;
        }
    }

    //Approach #3 (Better Dynamic Programming) [Accepted]
    public class Solution03 {
        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int[] dp = new int[cols + 1];
            int maxsqlen = 0, prev = 0;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    int temp = dp[j];
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                        maxsqlen = Math.max(maxsqlen, dp[j]);
                    } else {
                        dp[j] = 0;
                    }
                    prev = temp;
                }
            }
            return maxsqlen * maxsqlen;
        }
    }


//------------------------------------------------------------------------------////
    // 9Ch DP
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] f = new int[m][n];
        int i, j, t, res = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                    continue;
                }

                //A[i][j] = -1
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                    res = Math.max(1, res);
                    continue;
                }

                f[i][j] = Math.min(Math.min(f[i - 1][j], f[i - 1][j - 1]), f[i][j-1]) + 1;
                res = Math.max(f[i][j] * f[i][j], res);
            }
        }
        return res;
    }

    @Test
    public void test01() {
        /*
        1 0 1 0 0
        1 0 1 1 1
        1 1 1 1 1
        1 0 0 1 0
         */
        int[][] matrix = {{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
        System.out.println(maxSquare(matrix));

    }


//------------------------------------------------------------------------------////
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare2(int[][] matrix) {
        // write your code here
        int ans = 0;
        int n = matrix.length;
        int m;
        if(n > 0)
            m = matrix[0].length;
        else
            return ans;
        int [][]res = new int [n][m];
        for(int i = 0; i < n; i++){
            res[i][0] = matrix[i][0];
            ans = Math.max(res[i][0] , ans);
            for(int j = 1; j < m; j++) {
                if(i > 0) {
                    if(matrix[i][j] > 0) {
                        res[i][j] = Math.min(res[i - 1][j],Math.min(res[i][j-1], res[i-1][j-1])) + 1;
                    } else {
                        res[i][j] = 0;
                    }

                }
                else {
                    res[i][j] = matrix[i][j];
                }
                ans = Math.max(res[i][j], ans);
            }
        }
        return ans*ans;
    }

    @Test
    public void test02() {
        /*
        1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
         */
        int[][] matrix = {{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
        System.out.println(maxSquare2(matrix));

    }

//------------------------------------------------------------------------------////
//------------------------------------------------------------------------------////

}
/*
在一个二维01矩阵中找到全为1的最大正方形

样例
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
返回 4
 */


/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

Have you met this question in a real interview? Yes
Example
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
 */
