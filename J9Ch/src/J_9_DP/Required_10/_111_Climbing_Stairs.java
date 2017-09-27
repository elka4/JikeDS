package J_9_DP.Required_10;

/** 111 Climbing Stairs
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */
public class _111_Climbing_Stairs {

    public class Solution {
        public int climbStairs(int n) {
            if (n <= 1) {
                return 1;
            }
            int last = 1, lastlast = 1;
            int now = 0;
            for (int i = 2; i <= n; i++) {
                now = last + lastlast;
                lastlast = last;
                last = now;
            }
            return now;
        }
    }
}
