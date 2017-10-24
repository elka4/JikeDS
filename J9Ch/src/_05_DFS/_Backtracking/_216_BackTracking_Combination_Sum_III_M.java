package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _216_BackTracking_Combination_Sum_III_M {
    class Solution{
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

//        To make this faster, you can quit the for loop early and avoid unnecessary work.

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

}
