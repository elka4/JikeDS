package _01_Array.DP;

import java.util.LinkedList;

/*
LeetCode – Coin Change (Java)

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 */
public class Coin_Change {
/*    Java Solution 1 - Dynamic Programming

    Let dp[v] to be the minimum number of coins required to get the amount v.
            dp[i+a_coin] = min(dp[i+a_coin], dp[i]+1) if dp[i] is reachable.
    dp[i+a_coin] = dp[i+a_coin] is dp[i] is not reachable.
    We initially set dp[i] to be MAX_VALUE.
    Here is the Java code:*/

    public int coinChange(int[] coins, int amount) {
        if(amount==0) return 0;

        int[] dp = new int [amount+1];
        dp[0]=0; // do not need any coin to get 0 amount
        for(int i=1;i<=amount; i++)
            dp[i]= Integer.MAX_VALUE;

        for(int i=0; i<=amount; i++){
            for(int coin: coins){
                if(i+coin <=amount){
                    if(dp[i]==Integer.MAX_VALUE){
                        dp[i+coin] = dp[i+coin];
                    }else{
                        dp[i+coin] = Math.min(dp[i+coin], dp[i]+1);
                    }
                }
            }
        }

        if(dp[amount] >= Integer.MAX_VALUE)
            return -1;

        return dp[amount];
    }
   /* This clean solution takes time O(n^2).

    Java Solution 2 - Breath First Search (BFS)

    Most dynamic programming problems can be solved by using BFS.

    We can view this problem as going to a target position with steps that are allows in the array coins. We maintain two queues: one of the amount so far and the other for the minimal steps. The time is too much because of the contains method take n and total time is O(n^3).*/

    public int coinChange2(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        LinkedList<Integer> amountQueue = new LinkedList<Integer>();
        LinkedList<Integer> stepQueue = new LinkedList<Integer>();

        // to get 0, 0 step is required
        amountQueue.offer(0);
        stepQueue.offer(0);

        while (amountQueue.size() > 0) {
            int temp = amountQueue.poll();
            int step = stepQueue.poll();

            if (temp == amount)
                return step;

            for (int coin : coins) {
                if (temp > amount) {
                    continue;
                } else {
                    if (!amountQueue.contains(temp + coin)) {
                        amountQueue.offer(temp + coin);
                        stepQueue.offer(step + 1);
                    }
                }
            }
        }

        return -1;
    }

}
