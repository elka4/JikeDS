package HF.HF0_Intro;


//  70. Climbing Stairs
//  https://leetcode.com/problems/climbing-stairs/description/
//  http://www.lintcode.com/problem/climbing-stairs/
//  7:3
//假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，你能有多少种不同的方法爬到楼顶部？
public class _1ClimbStairs {
//------------------------------------------------------------------------
//https://leetcode.com/problems/climbing-stairs/solution/
//------------------------------------------------------------------------
    //3
    //Approach #3 Dynamic Programming [Accepted]
    public class Solution3 {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }

//------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public int climbStairs(int n) {
            return climb_Stairs(0, n);
        }
        public int climb_Stairs(int i, int n) {
            if (i > n) {
                return 0;
            }
            if (i == n) {
                return 1;
            }
            return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
        }
    }


//------------------------------------------------------------------------
    //2
    //Approach #2 Recursion with memorization [Accepted]
    public class Solution2 {
        public int climbStairs(int n) {
            int memo[] = new int[n + 1];
            return climb_Stairs(0, n, memo);
        }
        public int climb_Stairs(int i, int n, int memo[]) {
            if (i > n) {
                return 0;
            }
            if (i == n) {
                return 1;
            }
            if (memo[i] > 0) {
                return memo[i];
            }
            memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
            return memo[i];
        }
    }


//------------------------------------------------------------------------
    //4
    //Approach #4 Fibonacci Number [Accepted]:
    public class Solution4 {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;
        }
    }


//------------------------------------------------------------------------
    //5
    //Approach #5 Binets Method [Accepted]:
    public class Solution5 {
        public int climbStairs(int n) {
            int[][] q = {{1, 1}, {1, 0}};
            int[][] res = pow(q, n);
            return res[0][0];
        }
        public int[][] pow(int[][] a, int n) {
            int[][] ret = {{1, 0}, {0, 1}};
            while (n > 0) {
                if ((n & 1) == 1) {
                    ret = multiply(ret, a);
                }
                n >>= 1;
                a = multiply(a, a);
            }
            return ret;
        }
        public int[][] multiply(int[][] a, int[][] b) {
            int[][] c = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
                }
            }
            return c;
        }
    }


//------------------------------------------------------------------------
    //6
    //Approach #6 Fibonacci Formula [Accepted]:
    public class Solution6 {
        public int climbStairs(int n) {
            double sqrt5=Math.sqrt(5);
            double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
            return (int)(fibn/sqrt5);
        }
    }

//------------------------------------------------------------------------
    //7
    //9Ch
    public class Jiuzhang {
        public int climbStairs(int n) {
            if (n <= 1) {
                return n;
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


//------------------------------------------------------------------------
}
/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.


Example 1:

Input: 2
Output:  2
Explanation:  There are two ways to climb to the top.

1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output:  3
Explanation:  There are three ways to climb to the top.

1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
Seen this question in a real interview before?   Yes  No
Companies
Apple Adobe
Related Topics
Dynamic Programming
Java
 */