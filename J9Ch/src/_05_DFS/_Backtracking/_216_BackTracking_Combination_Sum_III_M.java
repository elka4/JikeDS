package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

// 216. Combination Sum III
public class _216_BackTracking_Combination_Sum_III_M {
    class Solution{
        //Simple and clean Java code, backtracking.
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            combination(ans, new ArrayList<Integer>(), k, 1, n);
            return ans;
        }

        private void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<Integer>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i <= 9; i++) {
                comb.add(i);
                combination(ans, comb, k, i+1, n-i);
                comb.remove(comb.size() - 1);
            }
        }

        // To make this faster, you can quit the for loop early and avoid unnecessary work.
        private void combination2(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
            if (comb.size() > k) {
                return;
            }
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<Integer>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i <= n && i<=9; i++) {

                comb.add(i);
                combination2(ans, comb, k, i+1, n-i);
                comb.remove(comb.size() - 1);

            }
        }

        //if you think for loop with multiple conditions is complex, you can add an if condition inside the for loop instead.
        private void combination3(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
            if (comb.size() > k) {
                return;
            }
            if (comb.size() == k && n == 0) {
                List<Integer> li = new ArrayList<Integer>(comb);
                ans.add(li);
                return;
            }
            for (int i = start; i<=9; i++) {
                if (n-i >= 0) {
                    comb.add(i);
                    combination3(ans, comb, k, i+1, n-i);
                    comb.remove(comb.size() - 1);
                }

            }
        }
    }
//------------------------------------------------------------------------------



//------------------------------------------------------------------------------



//------------------------------------------------------------------------------




}
/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers
 from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]




Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
 */