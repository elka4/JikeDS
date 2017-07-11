package S_7_Follow_Up.all;

import java.util.ArrayList;

/** 402 Continuous Subarray Sum


 * Created by tianhuizhu on 6/28/17.
 */
public class _402_Continuous_Subarray_Sum {

    public class Solution {
        /**
         * @param A an integer array
         * @return  A list of integers includes the index of the first number and the index of the last number
         */
        public ArrayList<Integer> continuousSubarraySum(int[] A) {
            // Write your code here
            ArrayList<Integer> result = new ArrayList<Integer>();
            result.add(0);
            result.add(0);

            int len = A.length;
            int start = 0, end = 0;
            int sum = 0;
            int ans = -0x7fffffff;
            for (int i = 0; i < len; ++i) {
                if (sum < 0) {
                    sum = A[i];
                    start = end = i;
                } else {
                    sum += A[i];
                    end = i;
                }
                if (sum >= ans) {
                    ans = sum;
                    result.set(0, start);
                    result.set(1, end);
                }
            }
            return result;
        }
    }
}
