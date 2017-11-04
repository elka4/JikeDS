package _09_Math;

/*
LeetCode – Integer Break (Java)

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 */
public class Integer_Break {

    /*
    Java Solution 1 - Dynamic Programming

Let dp[i] to be the max production value for breaking the number i. Since dp[i+j] can be i*j, dp[i+j] = max(max(dp[i], i) * max(dp[j], j)), dp[i+j]).
     */

    public int integerBreak(int n) {
        int[] dp = new int[n+1];

        for(int i=1; i<n; i++){
            for(int j=1; j<i+1; j++){
                if(i+j<=n){
                    dp[i+j]=Math.max(Math.max(dp[i],i)*Math.max(dp[j],j), dp[i+j]);
                }
            }
        }

        return dp[n];
    }

}
