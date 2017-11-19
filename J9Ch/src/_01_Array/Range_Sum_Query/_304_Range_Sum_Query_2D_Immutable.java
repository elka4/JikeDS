package _01_Array.Range_Sum_Query;

/* 304. Range Sum Query 2D - Immutable

Given a 2D matrix matrix, find the sum of the elements inside the
rectangle defined by its upper left corner (row1, col1) and lower
right corner (row2, col2).

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

public class _304_Range_Sum_Query_2D_Immutable {

    //https://leetcode.com/problems/range-sum-query-2d-immutable/solution/

    //Approach #1 (Brute Force) [Time Limit Exceeded]
    public class NumMatrix {
        private int[][] data;

        public NumMatrix(int[][] matrix) {
            data = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int r = row1; r <= row2; r++) {
                for (int c = col1; c <= col2; c++) {
                    sum += data[r][c];
                }
            }
            return sum;
        }
    }



//-------------------------------------------------------------------------////

    //Approach #2 (Caching) [Memory Limit Exceeded]





//-------------------------------------------------------------------------////

    //Approach #3 (Caching Rows) [Accepted]

    public class NumMatrix3 {
        private int[][] dp;

        public NumMatrix3(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            dp = new int[matrix.length][matrix[0].length + 1];
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    dp[r][c + 1] = dp[r][c] + matrix[r][c];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int row = row1; row <= row2; row++) {
                sum += dp[row][col2 + 1] - dp[row][col1];
            }
            return sum;
        }
    }




//-------------------------------------------------------------------------////

    //Approach #4 (Caching Smarter) [Accepted]

    public class NumMatrix4 {
        private int[][] dp;

        public NumMatrix4(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1]
                            + matrix[r][c] - dp[r][c];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] -
                    dp[row2 + 1][col1] + dp[row1][col1];
        }
    }




//-------------------------------------------------------------------------////

    //Clean and easy to understand java solution

    public class NumMatrix5 {
        private int[][] dp;

        public NumMatrix5(int[][] matrix) {
            if(   matrix           == null
                    || matrix.length    == 0
                    || matrix[0].length == 0   ){
                return;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            dp = new int[m + 1][n + 1];
            for(int i = 1; i <= m; i++){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] -
                            dp[i - 1][j - 1] + matrix[i - 1][j - 1] ;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int iMin = Math.min(row1, row2);
            int iMax = Math.max(row1, row2);

            int jMin = Math.min(col1, col2);
            int jMax = Math.max(col1, col2);

            return dp[iMax + 1][jMax + 1] - dp[iMax + 1][jMin] -
                    dp[iMin][jMax + 1] + dp[iMin][jMin];
        }
    }

//-------------------------------------------------------------------------////

    //Very clean and fast java solution

    public class NumMatrix6 {
        private int[][] sumRegion;

        public NumMatrix6(int[][] matrix) {
            if (matrix.length != 0)  sumRegion =
                    new int[matrix.length + 1][matrix[0].length + 1];

            for (int i = 0; i < matrix.length; i++) {
                int sum = 0;
                for (int j = 0; j < matrix[0].length; j++) {
                    sum += matrix[i][j];
                    sumRegion[i + 1][j + 1] = sum + sumRegion[i][j + 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumRegion[row2 + 1][col2 + 1] - sumRegion[row1][col2 + 1]
                    - sumRegion[row2 + 1][col1] + sumRegion[row1][col1];
        }
    }



//-------------------------------------------------------------------------////





}
