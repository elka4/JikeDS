package S_5_Dynamic_Problem_I.all;

/** 436 Maximal Square


 * Created by tianhuizhu on 6/28/17.
 */
public class _436_Maximal_Square {

    public class Solution {
        /**
         * @param matrix: a matrix of 0 and 1
         * @return: an integer
         */
        public int maxSquare1(int[][] matrix) {
            // write your code here
            int ans = 0;
            int n = matrix.length;
            int m;
            if(n > 0)
                m = matrix[0].length;
            else
                return ans;
            int [][]res = new int [n][m];
            for(int i = 0; i < n; i++){
                res[i][0] = matrix[i][0];
                ans = Math.max(res[i][0] , ans);
                for(int j = 1; j < m; j++) {
                    if(i > 0) {
                        if(matrix[i][j] > 0) {
                            res[i][j] = Math.min(res[i - 1][j],Math.min(res[i][j-1], res[i-1][j-1])) + 1;
                        } else {
                            res[i][j] = 0;
                        }

                    }
                    else {
                        res[i][j] = matrix[i][j];
                    }
                    ans = Math.max(res[i][j], ans);
                }
            }
            return ans*ans;
        }

        public int maxSquare2(int[][] matrix) {
            // write your code here
            int ans = 0;
            int n = matrix.length;
            int m;
            if(n > 0)
                m = matrix[0].length;
            else
                return ans;
            int [][]res = new int [2][m];
            for(int i = 0; i < n; i++){
                res[i%2][0] = matrix[i][0];
                ans = Math.max(res[i%2][0] , ans);
                for(int j = 1; j < m; j++) {
                    if(i > 0) {
                        if(matrix[i][j] > 0) {
                            res[i%2][j] = Math.min(res[(i - 1)%2][j],Math.min(res[i%2][j-1], res[(i-1)%2][j-1])) + 1;
                        } else {
                            res[i%2][j] = 0;
                        }

                    }
                    else {
                        res[i%2][j] = matrix[i%2][j];
                    }
                    ans = Math.max(res[i%2][j], ans);
                }
            }
            return ans*ans;
        }
    }
}
