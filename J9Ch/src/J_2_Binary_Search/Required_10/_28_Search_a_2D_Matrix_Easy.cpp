/**
 * 28.
 * Search a 2D Matrix
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */


class Solution {
public:
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    bool searchMatrix(vector<vector<int> > &matrix, int target) {
        int n = matrix.size();
        if (n == 0) {
            return false;
        }

        int m = matrix[0].size();
        if (m == 0) {
            return false;
        }

        int start = 0, end = n * m - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int row = mid / m;
            int col = mid % m;

            if (matrix[row][col] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[start / m][start % m] == target) {
            return true;
        }

        if (matrix[end / m][end % m] == target) {
            return true;
        }

        return false;
    }
};