package J_7_Two_Pointers.Optional_11;
import java.util.*;
/** 382 Triangle Count


 * Created by tianhuizhu on 6/28/17.
 */
public class _382_Triangle_Count_Medium {

    public class Solution {
        /**
         * @param S: A list of integers
         * @return: An integer
         */
        public int triangleCount(int S[]) {
            // write your code here
            int left = 0, right = S.length - 1;
            int ans = 0;
            Arrays.sort(S);
            for(int i = 0; i < S.length; i++) {
                left = 0;
                right = i - 1;
                while(left < right) {
                    if(S[left] + S[right] > S[i]) {
                        ans = ans + (right - left);
                        right --;
                    } else {
                        left ++;
                    }
                }
            }
            return ans;
        }
    }

}
