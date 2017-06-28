package J_5_Depth_First_Search.Related_7;
import java.util.*;
/** 197 Permutation Index
 * Created by tianhuizhu on 6/28/17.
 */
public class _197_Permutation_Index_Easy {

    public class Solution {

        long fac(int numerator) {
            long now = 1;
            for (int i = 1; i <= numerator; i++) {
                now *= (long) i;
            }
            return now;
        }

        long generateNum(HashMap<Integer, Integer> hash) {
            long denominator = 1;
            int sum = 0;
            for (int val : hash.values()) {
                if(val == 0 )
                    continue;
                denominator *= fac(val);
                sum += val;
            }
            if(sum==0) {
                return sum;
            }
            return fac(sum) / denominator;
        }
        /**
         * @param A an integer array
         * @return a long integer
         */
        public long permutationIndex(int[] A) {
            HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
            for (int i = 0; i < A.length; i++) {
                if (hash.containsKey(A[i]))
                    hash.put(A[i], hash.get(A[i]) + 1);
                else {
                    hash.put(A[i], 1);
                }
            }
            long ans = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = i + 1; j < A.length; j++) {
                    if (A[j] < A[i]) {
                        hash.put(A[j], hash.get(A[j]) - 1);
                        ans += generateNum(hash);
                        hash.put(A[j], hash.get(A[j]) + 1);
                    }
                }
                hash.put(A[i], hash.get(A[i])-1);
            }
            return ans+1;
        }
    }
}