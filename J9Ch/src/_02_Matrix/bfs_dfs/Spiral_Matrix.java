package _02_Matrix.bfs_dfs;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Spiral Matrix (Java)

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example, given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 */
public class Spiral_Matrix {

    /*Java Solution 1

    If more than one row and column left, it can form a circle and
    we process the circle. Otherwise, if only one row or column left,
    we process that column or row ONLY.*/

    public class Solution {
        public ArrayList<Integer> spiralOrder(int[][] matrix) {
            ArrayList<Integer> result = new ArrayList<Integer>();

            if(matrix == null || matrix.length == 0) return result;

            int m = matrix.length;
            int n = matrix[0].length;

            int x=0;
            int y=0;

            while(m>0 && n>0){

                //if one row/column left, no circle can be formed
                if(m==1){
                    for(int i=0; i<n; i++){
                        result.add(matrix[x][y++]);
                    }
                    break;
                }else if(n==1){
                    for(int i=0; i<m; i++){
                        result.add(matrix[x++][y]);
                    }
                    break;
                }

                //below, process a circle

                //top - move right
                for(int i=0;i<n-1;i++){
                    result.add(matrix[x][y++]);
                }

                //right - move down
                for(int i=0;i<m-1;i++){
                    result.add(matrix[x++][y]);
                }

                //bottom - move left
                for(int i=0;i<n-1;i++){
                    result.add(matrix[x][y--]);
                }

                //left - move up
                for(int i=0;i<m-1;i++){
                    result.add(matrix[x--][y]);
                }

                x++;
                y++;
                m=m-2;
                n=n-2;
            }

            return result;
        }
    }


    //Similarly, we can write the solution this way:

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix==null||matrix.length==0||matrix[0].length==0)
            return result;

        int m = matrix.length;
        int n = matrix[0].length;

        int left=0;
        int right=n-1;
        int top = 0;
        int bottom = m-1;

        while(result.size()<m*n){
            for(int j=left; j<=right; j++){
                result.add(matrix[top][j]);
            }
            top++;

            for(int i=top; i<=bottom; i++){
                result.add(matrix[i][right]);
            }
            right--;

            //prevent duplicate row
            if(bottom<top)
                break;

            for(int j=right; j>=left; j--){
                result.add(matrix[bottom][j]);
            }
            bottom--;

            // prevent duplicate column
            if(right<left)
                break;

            for(int i=bottom; i>=top; i--){
                result.add(matrix[i][left]);
            }
            left++;
        }

        return result;
    }


    /*Java Solution 2

    We can also recursively solve this problem.
    The solution's performance is not better than Solution 1.
    Therefore, Solution 1 should be preferred.*/

    public class Solution2 {
        public ArrayList<Integer> spiralOrder(int[][] matrix) {
            if(matrix==null || matrix.length==0)
                return new ArrayList<Integer>();

            return spiralOrder(matrix,0,0,matrix.length,matrix[0].length);
        }


        public ArrayList<Integer> spiralOrder(int [][] matrix, int x, int y, int m, int n){
            ArrayList<Integer> result = new ArrayList<Integer>();

            if(m<=0||n<=0)
                return result;

            //only one element left
            if(m==1&&n==1) {
                result.add(matrix[x][y]);
                return result;
            }

            //top - move right
            for(int i=0;i<n-1;i++){
                result.add(matrix[x][y++]);
            }

            //right - move down
            for(int i=0;i<m-1;i++){
                result.add(matrix[x++][y]);
            }

            //bottom - move left
            if(m>1){
                for(int i=0;i<n-1;i++){
                    result.add(matrix[x][y--]);
                }
            }

            //left - move up
            if(n>1){
                for(int i=0;i<m-1;i++){
                    result.add(matrix[x--][y]);
                }
            }

            if(m==1||n==1)
                result.addAll(spiralOrder(matrix, x, y, 1, 1));
            else
                result.addAll(spiralOrder(matrix, x+1, y+1, m-2, n-2));

            return result;
        }
    }

/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////
}
