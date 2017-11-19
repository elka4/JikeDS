package HF.HF3_Algo_DS_II_1BinarySearch;

//Search a 2D Matrix
public class _3Searcha2DMatrix {

    // Binary Search Twice
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



//-------------------------------------------------------------------------///////////

    // Binary Search Once
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length == 0){
            return false;
        }

        if(matrix[0] == null || matrix[0].length == 0){
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;

        int start = 0, end = row * column - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            int number = matrix[mid / column][mid % column];
            if(number == target){
                return true;
            }else if(number > target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        return false;

    }


//-------------------------------------------------------------------------///////////

}
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right. The first integer of each row is greater than the last integer of the previous row. For example,
Consider the following matrix:
[ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3, return true.
 */
