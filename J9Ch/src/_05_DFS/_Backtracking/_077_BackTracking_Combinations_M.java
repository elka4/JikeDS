package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _077_BackTracking_Combinations_M {
    public class Solution {
        public List<List<Integer>> combine(int n, int k) {
            if (k == n || k == 0) {
                List<Integer> row = new LinkedList<>();
                for (int i = 1; i <= k; ++i) {
                    row.add(i);
                }
                return new LinkedList<>(Arrays.asList(row));
            }
            List<List<Integer>> result = this.combine(n - 1, k - 1);
            result.forEach(e -> e.add(n));
            result.addAll(this.combine(n - 1, k));
            return result;
        }
    }


    class SOlution2{
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> combs = new ArrayList<List<Integer>>();
            combine(combs, new ArrayList<Integer>(), 1, n, k);
            return combs;
        }
        public void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
            if(k==0) {
                combs.add(new ArrayList<Integer>(comb));
                return;
            }
            for(int i=start;i<=n;i++) {
                comb.add(i);
                combine(combs, comb, i+1, n, k-1);
                comb.remove(comb.size()-1);
            }
        }
    }
}
