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
}
