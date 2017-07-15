package S_7_Follow_Up.Required_14;
import java.util.*;import java.util.*;
import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/** 558 Sliding Window Matrix Maximum


 * Created by tianhuizhu on 6/28/17.
 */
public class _558_Sliding_Window_Matrix_Maximum {

    public class Solution {
        /**
         * @param matrix an integer array of n * m matrix
         * @param k an integer
         * @return the maximum number
         */
        public int maxSlidingWindow2(int[][] matrix, int k) {
            // Write your code here
            int n = matrix.length;
            if (n == 0 || n < k)
                return 0;
            int m = matrix[0].length;
            if (m == 0 || m < k)
                return 0;

            int[][] sum = new int[n + 1][m + 1];
            for (int i = 0; i <= n; ++i) sum[i][0] = 0;
            for (int i = 0; i <= m; ++i) sum[0][i] = 0;

            for (int i = 1; i <= n; ++i)
                for (int j = 1; j <= m; ++j)
                    sum[i][j] = matrix[i - 1][j - 1] +
                            sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];

            int max_value = Integer.MIN_VALUE;
            for (int i = k; i <= n; ++i)
                for (int j = k; j <= m; ++j) {
                    int value = sum[i][j] - sum[i - k][j] -
                            sum[i][j - k] + sum[i - k][j - k];

                    if (value > max_value)
                        max_value = value;
                }
            return max_value;
        }
    }
}