package J_2_Binary_Search.all;

/**
 * Created by tianhuizhu on 7/2/17.
 */
public class _38_Search_a_2D_Matrix_II {

    public class Solution {
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
}
