package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _254_BackTracking_Factor_Combinations_M {

//My Recursive DFS Java Solution

    class Solution{
        public List<List<Integer>> getFactors(int n) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            helper(result, new ArrayList<Integer>(), n, 2);
            return result;
        }

        public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
            if (n <= 1) {
                if (item.size() > 1) {
                    result.add(new ArrayList<Integer>(item));
                }
                return;
            }

            for (int i = start; i <= n; ++i) {
                if (n % i == 0) {
                    item.add(i);
                    helper(result, item, n/i, i);
                    item.remove(item.size()-1);
                }
            }
        }
    }

    class Solution2{
        public List<List<Integer>> getFactors(int n) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (n <= 3) return result;
            helper(n, -1, result, new ArrayList<Integer>());
            return result;
        }

        public void helper(int n, int lower, List<List<Integer>> result, List<Integer> cur) {
            if (lower != -1) {
                cur.add(n);
                result.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size() - 1);
            }
            int upper = (int) Math.sqrt(n);
            for (int i = Math.max(2, lower); i <= upper; ++i) {
                if (n % i == 0) {
                    cur.add(i);
                    helper(n / i, i, result, cur);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

}
