package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _494_DFS_Target_Sum_M {
    //DP
    public class Solution {
        public int findTargetSumWays(int[] nums, int s) {
            int sum = 0;
            for(int i: nums) sum+=i;
            if(s>sum || s<-sum) return 0;
            int[] dp = new int[2*sum+1];
            dp[0+sum] = 1;
            for(int i = 0; i<nums.length; i++){
                int[] next = new int[2*sum+1];
                for(int k = 0; k<2*sum+1; k++){
                    if(dp[k]!=0){
                        next[k + nums[i]] += dp[k];
                        next[k - nums[i]] += dp[k];
                    }
                }
                dp = next;
            }
            return dp[sum+s];
        }
    }

    //Java simple DFS with memorization


    public class Solution2 {
        public int findTargetSumWays(int[] nums, int S) {
            if (nums == null || nums.length == 0){
                return 0;
            }
            return helper(nums, 0, 0, S, new HashMap<>());
        }
        private int helper(int[] nums, int index, int sum, int S, Map<String, Integer> map){
            String encodeString = index + "->" + sum;
            if (map.containsKey(encodeString)){
                return map.get(encodeString);
            }
            if (index == nums.length){
                if (sum == S){
                    return 1;
                }else {
                    return 0;
                }
            }
            int curNum = nums[index];
            int add = helper(nums, index + 1, sum - curNum, S, map);
            int minus = helper(nums, index + 1, sum + curNum, S, map);
            map.put(encodeString, add + minus);
            return add + minus;
        }
    }
//-------------------------------------------------------------------------///
//Approach #2 Recursion with memoization [Accepted]

    public class leet2 {
        int count = 0;
        public int findTargetSumWays(int[] nums, int S) {
            int[][] memo = new int[nums.length][2001];
            for (int[] row: memo)
                Arrays.fill(row, Integer.MIN_VALUE);
            return calculate(nums, 0, 0, S, memo);
        }
        public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
            if (i == nums.length) {
                if (sum == S)
                    return 1;
                else
                    return 0;
            } else {
                if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                    return memo[i][sum + 1000];
                }
                int add = calculate(nums, i + 1, sum + nums[i], S, memo);
                int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
                memo[i][sum + 1000] = add + subtract;
                return memo[i][sum + 1000];
            }
        }
    }


//Approach #3 2D Dynamic Programming [Accepted]
public class leet3 {
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }
}


//Approach #4 1D Dynamic Programming [Accepted]:

    public class leet4 {
        public int findTargetSumWays(int[] nums, int S) {
            int[] dp = new int[2001];
            dp[nums[0] + 1000] = 1;
            dp[-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                int[] next = new int[2001];
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[sum + 1000] > 0) {
                        next[sum + nums[i] + 1000] += dp[sum + 1000];
                        next[sum - nums[i] + 1000] += dp[sum + 1000];
                    }
                }
                dp = next;
            }
            return S > 1000 ? 0 : dp[S + 1000];
        }
    }



//-------------------------------------------------------------------------///






}
/*

 */