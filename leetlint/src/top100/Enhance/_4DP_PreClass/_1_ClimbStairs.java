package top100.Enhance._4DP_PreClass;

public class _1_ClimbStairs {
	public int climbStairs(int n) {
	        if (n <= 0) {
	            return 0;
	        }
	        if (n == 1) {
	            return 1;
	        }
	
	        int pre = 1;
	        int cur = 2;
	
	        for (int i = 2; i < n; i++) {
	            int tmp = cur;
	            cur = cur + pre;
	            pre = tmp;
	        }
	        return cur;
	}
}
