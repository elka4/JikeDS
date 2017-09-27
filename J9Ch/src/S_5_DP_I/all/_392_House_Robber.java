package S_5_DP_I.all;

/** 392 House Robber
 * Created by tianhuizhu on 6/28/17.
 */
public class _392_House_Robber {

    public class Solution {
        /**
         * @param A: An array of non-negative integers.
         * return: The maximum amount of money you can rob tonight
         */
        //---方法一---
        public long houseRobber1(int[] A) {
            // write your code here
            int n = A.length;
            if(n == 0)
                return 0;
            long []res = new long[n+1];


            res[0] = 0;
            res[1] = A[0];
            for(int i = 2; i <= n; i++) {
                res[i] = Math.max(res[i-1], res[i-2] + A[i-1]);
            }
            return res[n];
        }
        //---方法二---
        public long houseRobber2(int[] A) {
            // write your code here
            int n = A.length;
            if(n == 0)
                return 0;
            long []res = new long[2];


            res[0] = 0;
            res[1] = A[0];
            for(int i = 2; i <= n; i++) {
                res[i%2] = Math.max(res[(i-1)%2], res[(i-2)%2] + A[i-1]);
            }
            return res[n%2];
        }
    }
}
