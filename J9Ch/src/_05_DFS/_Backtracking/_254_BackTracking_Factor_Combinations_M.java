package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//254. Factor Combinations

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

    //A simple java solution
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
/////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////



}
/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples:
input: 1
output:
[]
input: 37
output:
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */