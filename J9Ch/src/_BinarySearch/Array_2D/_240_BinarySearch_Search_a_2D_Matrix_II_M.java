package _BinarySearch.Array_2D;

//  240. Search a 2D Matrix II
//  https://leetcode.com/problems/search-a-2d-matrix-ii/description/
//  Binary Search, Divide and Conquer
//  3:3
public class _240_BinarySearch_Search_a_2D_Matrix_II_M {
//-----------------------------------------------------------------------------
    //1
    // 9Ch
    public class Jiuzhang{
        /**
         * @param matrix: A list of lists of integers
         * @param: A number you want to search in the matrix
         * @return: An integer indicate the occurrence of target in the given matrix
         */
        public int searchMatrix(int[][] matrix, int target) {
            // check corner case
            if (matrix == null || matrix.length == 0) {
                return 0;
            }
            if (matrix[0] == null || matrix[0].length == 0) {
                return 0;
            }

            // from bottom left to top right
            int n = matrix.length;
            int m = matrix[0].length;
            int x = n - 1;
            int y = 0;
            int count = 0;

            while (x >= 0 && y < m) {
                if (matrix[x][y] < target) {
                    y++;
                } else if (matrix[x][y] > target) {
                    x--;
                } else {
                    count++;
                    x--;
                    y++;
                }
            }
            return count;
        }
    }

//-----------------------------------------------------------------------------
    //2
    // version: 高频题班
    public class Jiuzhang2 {
        /**
         * @param matrix: A list of lists of integers
         * @param: A number you want to search in the matrix
         * @return: An integer indicate the occurrence of target in the given matrix
         */
        public int searchMatrix(int[][] matrix, int target) {
            // write your code here
            int r = matrix.length - 1;
            int c = 0;
            int ans = 0;
            while (r >= 0 && c < matrix[0].length) {
                if (target == matrix[r][c]) {
                    ans++;
                    r--;
                    c++;
                    continue;
                }
                if (target < matrix[r][c]) {
                    r--;
                } else {
                    c++;
                }
            }
            return ans;
        }
    }

//-----------------------------------------------------------------------------
    //3
/*My concise O(m+n) Java solution
    We start search the matrix from top right corner, initialize the current position to top right corner, if the target is greater than the value in current position, then the target can not be in entire row of current position because the row is sorted, if the target is less than the value in current position, then the target can not in the entire column because the column is sorted too. We can rule out one row or one column each time, so the time complexity is O(m+n).*/

    public class Solution3 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
                return false;
            }
            int col = matrix[0].length-1;
            int row = 0;
            while(col >= 0 && row <= matrix.length-1) {
                if(target == matrix[row][col]) {
                    return true;
                } else if(target < matrix[row][col]) {
                    col--;
                } else if(target > matrix[row][col]) {
                    row++;
                }
            }
            return false;
        }
    }
//-----------------------------------------------------------------------------
}
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */