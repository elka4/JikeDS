package J_9_Dynamic_Programming.all;

import java.util.Arrays;
import java.util.Comparator;

/** 602 Russian Doll Envelopes


 * Created by tianhuizhu on 6/28/17.
 */
public class _602_Russian_Doll_Envelopes {

    public class Solution {
        /**
         * @param envelopes a number of envelopes with widths and heights
         * @return the maximum number of envelopes
         */
        public int maxEnvelopes(int[][] envelopes) {
            // Write your code here
            if(envelopes == null || envelopes.length == 0
                    || envelopes[0] == null || envelopes[0].length != 2)
                return 0;
            Arrays.sort(envelopes, new Comparator<int[]>(){
                public int compare(int[] arr1, int[] arr2){
                    if(arr1[0] == arr2[0])
                        return arr2[1] - arr1[1];
                    else
                        return arr1[0] - arr2[0];
                }
            });
            int dp[] = new int[envelopes.length];
            int len = 0;
            for(int[] envelope : envelopes){
                int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if(index < 0)
                    index = -index - 1;
                dp[index] = envelope[1];
                if (index == len)
                    len++;
            }
            return len;
        }
    }
}
