package _02_Matrix.bfs_dfs;

/*
LeetCode – Range Sum Query 2D – Immutable (Java)

Given a 2D matrix matrix, find the sum of the elements inside the rectangle
defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Analysis

Since the assumption is that there are many calls to sumRegion method,
we should use some extra space to store the intermediate results.
Here we define an array sum[][] which stores the sum value from (0,0) to the current cell.
 */


public class Range_Sum_Query_2D_Immutable {
    public class NumMatrix {
        int [][] sum;

        public NumMatrix(int[][] matrix) {
            if(matrix==null || matrix.length==0||matrix[0].length==0)
                return;

            int m = matrix.length;
            int n = matrix[0].length;
            sum = new int[m][n];

            for(int i=0; i<m; i++){
                int sumRow=0;
                for(int j=0; j<n; j++){
                    if(i==0){
                        sumRow += matrix[i][j];
                        sum[i][j]=sumRow;
                    }else{
                        sumRow += matrix[i][j];
                        sum[i][j]=sumRow+sum[i-1][j];
                    }

                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(this.sum==null)
                return 0;

            int topRightX = row1;
            int topRightY = col2;

            int bottomLeftX=row2;
            int bottomLeftY= col1;

            int result=0;

            if(row1==0 && col1==0){
                result = sum[row2][col2];
            }else if(row1==0){
                result = sum[row2][col2]
                        -sum[bottomLeftX][bottomLeftY-1];

            }else if(col1==0){
                result = sum[row2][col2]
                        -sum[topRightX-1][topRightY];
            }else{
                result = sum[row2][col2]
                        -sum[topRightX-1][topRightY]
                        -sum[bottomLeftX][bottomLeftY-1]
                        +sum[row1-1][col1-1];
            }

            return result;
        }
    }

//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------
}
