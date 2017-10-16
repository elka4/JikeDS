package DP.DP7;

import java.util.Stack;
/*
-----------------------------------------------------------------------------------------------
LintCode 510 Maximum Rectangle

• 题意:
• 给定一个01矩阵，找到其中面积最大的全1长方形
• 例子:
• 输入:
– 11001
– 01001
– 00111
– 00111
– 00001
• 输出:6
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最优策略产生最大的全1长方形
• 最后一步:观察它的最后一行的1
• 每一个1向上延伸一直到边界或0
• 一定至少有一根顶梁柱
    – 只能到达长方形上边缘
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 这根顶梁柱向左最多到达长方形左边缘
• 向右最多到达长方形右边缘
-----------------------------------------------------------------------------------------------
子问题

• 求一个格子(i, j)的顶梁柱长度up[i][j]
    – 即往上最多延伸多少个1
• 如果(i, j)是0，则up[i][j] = 0
• 如果(i, j)是1，则up[i][j] = up[i-1][j] + 1

• 求一个格子(i, j)的顶梁柱最多往左延伸多久
– left[i][j]
• 如果(i, j)是0，则left[i][j] = 0
• 如果上面一个格子(i-1, j)是0，则left[i][j]=(i, j)往左最多连续有多少个1
• 否则left[i][j] = min{(i, j)往左最多连续有多少个1，(i-1, j)的顶梁柱最多往 左延伸多久}
• 同理求出right[i][j]

• 求(i, j)往左最多连续有多少个1
• 如果(i, j)是0，答案是0
• 如果(i, j)是1，答案是1 + (i, j-1)往左最多连续有多少个1
• 同理求出(i, j)往右最多连续有多少个1

-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• 对于up，逐行求，每一行从左到右
• 对于left，同理
• 对于right，每一行从右到左
• 答案是maxi,j{up[i][j] * (left[i][j] + right[i][j] – 1)}
• 时间复杂度O(MN)，空间复杂度O(MN)，可以滚动数组优化至O(N)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

//Maximal Rectangle
public class _7MaximalRectangle {
    /**
     * @param matrix a boolean 2D matrix
     * @return an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        // Write your code here
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int[][] height = new int[m][n + 1];

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (! matrix[i][j]) {
                    height[i][j] = 0;
                } else {
                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int area = maxAreaInHist(height[i]);
            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    private int maxAreaInHist(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();

        int i = 0;
        int max = 0;

        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                max = Math.max(max, height[t]
                        * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return max;
    }



////////////////////////////////////////////////////////////////////////////

    // 动态规划专题班版本
    /**
     * @param A a boolean 2D matrix
     * @return an integer
     */
    public int maximalRectangle2(boolean[][] A) {
        // A is boolean
        // when calculating left and right, check A[i-1][j] is true
        if (A==null||A.length==0||A[0].length==0) {
            return 0;
        }
        int m = A.length;
        int n = A[0].length;
        int[][] up = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        int i, j, k, l, r, res = 0;

        for (i=0; i<m; ++i) {
            // calc up
            for (j=0; j<n; ++j) {
                if (!A[i][j]) {
                    up[i][j] = 0;
                }
                else {
                    up[i][j] = 1;
                    if (i>0) {
                        up[i][j] += up[i-1][j];
                    }
                }
            }

            // calc left
            l = 0;
            for (j=0; j<n; ++j) {
                if (!A[i][j]) {
                    l = left[i][j] = 0;
                }
                else {
                    ++l;
                    left[i][j] = l;
                    if (i>0&&A[i-1][j]&&left[i-1][j] < left[i][j]) {
                        left[i][j] = left[i-1][j];
                    }
                }
            }

            // calc right
            r=0;
            for (j=n-1; j>=0; --j) {
                if (!A[i][j]) {
                    r = right[i][j] = 0;
                }
                else {
                    ++r;
                    right[i][j] = r;
                    if (i>0&&A[i-1][j]&&right[i-1][j] < right[i][j]) {
                        right[i][j] = right[i-1][j];
                    }
                }
            }
        }

        for (i=0; i<m; ++i) {
            for (j=0; j<n; ++j) {
                res = Math.max(res, up[i][j] * (left[i][j] + right[i][j] - 1));
            }
        }

        return res;
    }

////////////////////////////////////////////////////////////////////////////

}
/*
Given a 2D boolean matrix filled with False and True, find the largest rectangle containing all True and return its area.

Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1, 1, 0, 0, 1],
  [0, 1, 0, 0, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1]
]
return 6.


 */
