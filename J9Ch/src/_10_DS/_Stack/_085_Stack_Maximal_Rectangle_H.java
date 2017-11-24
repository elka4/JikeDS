package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _085_Stack_Maximal_Rectangle_H {
/*    A O(n^2) solution based on Largest Rectangle in Histogram
    This question is similar as [Largest Rectangle in Histogram]:

    You can maintain a row length of Integer array H recorded its height of '1's, and scan and update row by row to find out the largest rectangle of each row.

    For each row, if matrix[row][i] == '1'. H[i] +=1, or reset the H[i] to zero.
    and accroding the algorithm of [Largest Rectangle in Histogram], to update the maximum area.*/

    public class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix==null||matrix.length==0||matrix[0].length==0)
                return 0;
            int cLen = matrix[0].length;    // column length
            int rLen = matrix.length;       // row length
            // height array
            int[] h = new int[cLen+1];
            h[cLen]=0;
            int max = 0;


            for (int row=0;row<rLen;row++) {
                Stack<Integer> s = new Stack<Integer>();
                for (int i=0;i<cLen+1;i++) {
                    if (i<cLen)
                        if(matrix[row][i]=='1')
                            h[i]+=1;
                        else h[i]=0;

                    if (s.isEmpty()||h[s.peek()]<=h[i])
                        s.push(i);
                    else {
                        while(!s.isEmpty()&&h[i]<h[s.peek()]){
                            int top = s.pop();
                            int area = h[top]*(s.isEmpty()?i:(i-s.peek()-1));
                            if (area>max)
                                max = area;
                        }
                        s.push(i);
                    }
                }
            }
            return max;
        }
    }



/*
    My java solution based on Maximum Rectangle in Histogram with explanation
    We can apply the maximum in histogram in each row of the 2D matrix. What we need is to maintain an int array for each row, which represent for the height of the histogram.

    Please refer to https://leetcode.com/problems/largest-rectangle-in-histogram/ first.

    Suppose there is a 2D matrix like

        1 1 0 1 0 1

        0 1 0 0 1 1

        1 1 1 1 0 1

        1 1 1 1 0 1

    First initiate the height array as 1 1 0 1 0 1, which is just a copy of the first row. Then we can easily calculate the max area is 2.

    Then update the array. We scan the second row, when the matrix[1][i] is 0, set the height[i] to 0; else height[i] += 1, which means the height has increased by 1. So the height array again becomes 0 2 0 0 1 2. The max area now is also 2.

    Apply the same method until we scan the whole matrix. the last height arrays is 2 4 2 2 0 4, so the max area has been found as 2 * 4 = 8.

    Then reason we scan the whole matrix is that the maximum value may appear in any row of the height.

    Code as follows:*/

    public class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

            int[] height = new int[matrix[0].length];
            for(int i = 0; i < matrix[0].length; i ++){
                if(matrix[0][i] == '1') height[i] = 1;
            }
            int result = largestInLine(height);
            for(int i = 1; i < matrix.length; i ++){
                resetHeight(matrix, height, i);
                result = Math.max(result, largestInLine(height));
            }

            return result;
        }

        private void resetHeight(char[][] matrix, int[] height, int idx){
            for(int i = 0; i < matrix[0].length; i ++){
                if(matrix[idx][i] == '1') height[i] += 1;
                else height[i] = 0;
            }
        }

        public int largestInLine(int[] height) {
            if(height == null || height.length == 0) return 0;
            int len = height.length;
            Stack<Integer> s = new Stack<Integer>();
            int maxArea = 0;
            for(int i = 0; i <= len; i++){
                int h = (i == len ? 0 : height[i]);
                if(s.isEmpty() || h >= height[s.peek()]){
                    s.push(i);
                }else{
                    int tp = s.pop();
                    maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                    i--;
                }
            }
            return maxArea;
        }
    }
//------------------------------------------------------------------------------///////
    // 9Ch
    public class Jiuzhang1 {
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
    }

    // 动态规划专题班版本
    public class Jiuzhang2 {
        /**
         * @param A a boolean 2D matrix
         * @return an integer
         */
        public int maximalRectangle(boolean[][] A) {
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
    }
}
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

 */

/*
给你一个二维矩阵，权值为False和True，找到一个最大的矩形，使得里面的值全部为True，输出它的面积

您在真实的面试中是否遇到过这个题？ Yes
样例
给你一个矩阵如下

[
  [1, 1, 0, 0, 1],
  [0, 1, 0, 0, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1]
]
输出6
 */