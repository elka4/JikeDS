package S_1_FollowUp.Optional_6;

import java.util.Arrays;

/** 382 Triangle Count


 * Created by tianhuizhu on 6/28/17.
 */
public class _382_Triangle_Count {

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
