package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _473_DFS_Matchsticks_to_Square_M {

    public class Solution {
        public boolean makesquare(int[] nums) {
            if (nums == null || nums.length < 4) return false;
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % 4 != 0) return false;

            return dfs(nums, new int[4], 0, sum / 4);
        }

        private boolean dfs(int[] nums, int[] sums, int index, int target) {
            if (index == nums.length) {
                if (sums[0] == target && sums[1] == target && sums[2] == target) {
                    return true;
                }
                return false;
            }

            for (int i = 0; i < 4; i++) {
                if (sums[i] + nums[index] > target) continue;
                sums[i] += nums[index];
                if (dfs(nums, sums, index + 1, target)) return true;
                sums[i] -= nums[index];
            }

            return false;
        }
    }


    public class Solution2 {
        public boolean makesquare(int[] nums) {
            if (nums == null || nums.length < 4) return false;
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % 4 != 0) return false;

            Arrays.sort(nums);
            reverse(nums);

            return dfs(nums, new int[4], 0, sum / 4);
        }

        private boolean dfs(int[] nums, int[] sums, int index, int target) {
            if (index == nums.length) {
                if (sums[0] == target && sums[1] == target && sums[2] == target) {
                    return true;
                }
                return false;
            }

            for (int i = 0; i < 4; i++) {
                if (sums[i] + nums[index] > target) continue;
                sums[i] += nums[index];
                if (dfs(nums, sums, index + 1, target)) return true;
                sums[i] -= nums[index];
            }

            return false;
        }

        private void reverse(int[] nums) {
            int i = 0, j = nums.length - 1;
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++; j--;
            }
        }
    }


//----------------------------------------------------------------------------
    //Approach #3 Using Recursion [Time Limit Exceeded]
    public class leet3 {
        public int findMaxForm(String[] strs, int m, int n) {
            return calculate(strs, 0, m, n);
        }
        public int calculate(String[] strs, int i, int zeroes, int ones) {
            if (i == strs.length)
                return 0;
            int[] count = countzeroesones(strs[i]);
            int taken = -1;
            if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
                taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]) + 1;
            int not_taken = calculate(strs, i + 1, zeroes, ones);
            return Math.max(taken, not_taken);
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }


    //    Approach #4 Using Memoization [Accepted]
    public class leet4 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][][] memo = new int[strs.length][m + 1][n + 1];
            return calculate(strs, 0, m, n, memo);
        }
        public int calculate(String[] strs, int i, int zeroes, int ones, int[][][] memo) {
            if (i == strs.length)
                return 0;
            if (memo[i][zeroes][ones] != 0)
                return memo[i][zeroes][ones];
            int[] count = countzeroesones(strs[i]);
            int taken = -1;
            if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
                taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1;
            int not_taken = calculate(strs, i + 1, zeroes, ones, memo);
            memo[i][zeroes][ones] = Math.max(taken, not_taken);
            return memo[i][zeroes][ones];
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }


//----------------------------------------------------------------------------






}
/*
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 */