package J_5_Depth_First_Search.Related_7;
import java.util.*;
/** 211 String Permutation
 * Created by tianhuizhu on 6/28/17.
 */
public class _211_String_Permutation_Easy {

    public class Solution {
        /**
         * @param A a string
         * @param B a string
         * @return a boolean
         */
        public boolean stringPermutation(String A, String B) {
            // Write your code here
            int[] cnt = new int[1000];
            for (int i = 0; i < A.length(); ++i)
                cnt[(int)A.charAt(i)] += 1;
            for (int i = 0; i < B.length(); ++i)
                cnt[(int)B.charAt(i)] -= 1;
            for (int i = 0; i < 1000; ++i)
                if (cnt[i] != 0)
                    return false;
            return true;
        }
    }
}
