package _02_Matrix.bfs_dfs;

/* LeetCode – Set Matrix Zeroes (Java)

http://www.programcreek.com/2012/12/leetcode-set-matrix-zeroes-java/



Given a m * n matrix, if an element is 0, set its entire row and column to 0.
Do it in place.

Analysis

This problem should be solved in place, i.e., no other array should be used. We can use the first column and the first row to track if a row/column should be set to 0.

Since we used the first row and first column to mark the zero row/column, the original values are changed.

Specifically, given, the following matrix
 */


public class Set_Matrix_Zeroes {

    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        //set first row and column zero or not
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0] == 0){
                firstColumnZero = true;
                break;
            }
        }

        for(int i=0; i<matrix[0].length; i++){
            if(matrix[0][i] == 0){
                firstRowZero = true;
                break;
            }
        }

        //mark zeros on first row and column
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //use mark to set elements
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        //set first column and row
        if(firstColumnZero){
            for(int i=0; i<matrix.length; i++)
                matrix[i][0] = 0;
        }

        if(firstRowZero){
            for(int i=0; i<matrix[0].length; i++)
                matrix[0][i] = 0;
        }

    }

//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///
}
