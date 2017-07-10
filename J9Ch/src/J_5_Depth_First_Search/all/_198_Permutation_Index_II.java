package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/** 198 Permutation Index II
 * Created by tianhuizhu on 6/28/17.
 */
public class _198_Permutation_Index_II {

    // version 1
    //
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
    public long permutationIndexII(int[] A) {
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
            HashMap<Integer, Integer> flag = new HashMap<Integer, Integer>();

            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[i] && !flag.containsKey(A[j])) {
                    flag.put(A[j], 1);
                    hash.put(A[j], hash.get(A[j])-1);
                    ans += generateNum(hash);
                    hash.put(A[j], hash.get(A[j])+1);
                }

            }
            hash.put(A[i], hash.get(A[i])-1);
        }
        return ans + 1;
    }

    //Given the permutation [1, 4, 2, 2], return 3.
    @Test
    public void test01(){
        int[] nums = {1, 4, 2, 2};
        System.out.println(permutationIndexII(nums));
    }







    // version 2
    public class Solution2 {
        /**
         * @param A an integer array
         * @return a long integer
         */
        public long permutationIndexII(int[] A) {
            if (A == null || A.length == 0)
                return 0L;

            Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
            long index = 1, fact = 1, multiFact = 1;
            for (int i = A.length - 1; i >= 0; i--) {
                if (counter.containsKey(A[i])) {
                    counter.put(A[i], counter.get(A[i]) + 1);
                    multiFact *= counter.get(A[i]);
                } else {
                    counter.put(A[i], 1);
                }

                int rank = 0;
                for (int j = i + 1; j < A.length; j++) {
                    if (A[i] > A[j]) rank++;
                }

                index += rank * fact / multiFact;
                fact *= (A.length - i);
            }

            return index;
        }
    }
}
