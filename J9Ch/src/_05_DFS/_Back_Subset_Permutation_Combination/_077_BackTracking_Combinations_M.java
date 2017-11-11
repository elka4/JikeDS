package _05_DFS._Back_Subset_Permutation_Combination;
import java.util.*;

//77. Combinations

public class _077_BackTracking_Combinations_M {
    //A short recursive Java solution based on C(n,k)=C(n-1,k-1)+C(n-1,k)
/*    Basically, this solution follows the idea of the mathematical formula C(n,k)=C(n-1,k-1)+C(n-1,k).

    Here C(n,k) is divided into two situations. Situation one, number n is selected, so we only need to select k-1 from n-1 next. Situation two, number n is not selected, and the rest job is selecting k from n-1.*/
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

    //Backtracking Solution Java
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

    //DP for the problem
    public class Solution3
    {
        // Combine(n, n).
        private List<Integer> allContain(int n)
        {
            final List<Integer> result = new ArrayList<>();
            for (int i = 1; i <= n; ++i)
            {
                result.add(i);
            }

            return result;
        }

        public List<List<Integer>> combine(int n, int k)
        {
            List<List<List<Integer>>> previous = new ArrayList<>();

            for (int i = 0; i <= n; ++i)
            {
                previous.add(Collections.singletonList(Collections.<Integer>emptyList()));
            }

            for (int i = 1; i <= k; ++i)
            {
                final List<List<List<Integer>>> current = new ArrayList<>();
                current.add(Collections.singletonList(allContain(i)));

                // Combine(i, j).
                for (int j = i + 1; j <= n; ++j)
                {
                    final List<List<Integer>> list = new ArrayList<>();

                    // Combine(i, j - 1).
                    list.addAll(current.get(current.size() - 1));

                    // Comine(i - 1, j - 1).
                    for (final List<Integer> item : previous.get(current.size()))
                    {
                        final List<Integer> newItem = new ArrayList<>(item);
                        newItem.add(j);
                        list.add(newItem);
                    }

                    current.add(list);
                }

                previous = current;
            }

            return (previous.size() == 0) ? Collections.<List<Integer>>emptyList() : previous.get(previous.size() - 1);
        }
    }


    //3 ms Java Solution
    public class Solution4 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (k > n || k < 0) {
                return result;
            }
            if (k == 0) {
                result.add(new ArrayList<Integer>());
                return result;
            }
            result = combine(n - 1, k - 1);
            for (List<Integer> list : result) {
                list.add(n);
            }
            result.addAll(combine(n - 1, k));
            return result;
        }
    }

    //DFS recursive Java Solution
    //A DFS idea with back-trace. Very straightforward.
    public class Solution5 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> rslt = new ArrayList<List<Integer>>();
            dfs(new Stack<Integer>(), 1, n, k, rslt);
            return rslt;
        }

        private void dfs(Stack<Integer> path, int index, int n, int k, List<List<Integer>> rslt){
            // ending case
            if(k==0){
                List<Integer> list = new ArrayList<Integer>(path);
                rslt.add(list);
                return;
            }
            // recursion case
            for(int i = index;i <= n;i++){
                path.push(i);
                dfs(path, i+1, n, k-1, rslt);
                path.pop();
            }
        }
    }




///////////////////////////////////////////////////////////////////////////////////////

    //jiuzhang
    public class Jiuzhang {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> rst = new ArrayList<List<Integer>>();
            List<Integer> solution = new ArrayList<Integer>();

            helper(rst, solution, n, k, 1);
            return rst;
        }

        private void helper(List<List<Integer>> rst, List<Integer> solution,
                            int n, int k, int start) {
            if (solution.size() == k){
                rst.add(new ArrayList(solution));
                return;
            }

            for(int i = start; i<= n; i++){
                solution.add(i);

                // the new start should be after the next number after i
                helper(rst, solution, n, k, i + 1);
                solution.remove(solution.size() - 1);
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////



}
/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

 */

/*
lint

组给出两个整数n和k，返回从1......n中选出的k个数的组合。
您在真实的面试中是否遇到过这个题？
样例

例如 n = 4 且 k = 2

返回的解为：

[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]

 */