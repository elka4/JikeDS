package J_2_Binary_Search.Required_10;
import java.util.*;
/**
 * 28.
 * Search a 2D Matrix
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _28_Search_a_2D_Matrix {

    // Binary Search Twice
    public class Solution1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            if (matrix[0] == null || matrix[0].length == 0) {
                return false;
            }

            int row = matrix.length;
            int column = matrix[0].length;

            // find the row index, the last number <= target
            int start = 0, end = row - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (matrix[mid][0] == target) {
                    return true;
                } else if (matrix[mid][0] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (matrix[end][0] <= target) {
                row = end;
            } else if (matrix[start][0] <= target) {
                row = start;
            } else {
                return false;
            }

            // find the column index, the number equal to target
            start = 0;
            end = column - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (matrix[row][mid] == target) {
                    return true;
                } else if (matrix[row][mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (matrix[row][start] == target) {
                return true;
            } else if (matrix[row][end] == target) {
                return true;
            }
            return false;
        }
    }

    // Binary Search Once
    public class Solution2 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            if (matrix[0] == null || matrix[0].length == 0) {
                return false;
            }

            int row = matrix.length, column = matrix[0].length;
            int start = 0, end = row * column - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                int number = matrix[mid / column][mid % column];
                if (number == target) {
                    return true;
                } else if (number < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (matrix[start / column][start % column] == target) {
                return true;
            } else if (matrix[end / column][end % column] == target) {
                return true;
            }

            return false;
        }
    }
}
