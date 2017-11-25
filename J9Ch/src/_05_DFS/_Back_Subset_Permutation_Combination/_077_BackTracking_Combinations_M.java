package _05_DFS._Back_Subset_Permutation_Combination;
import org.junit.Test;

import java.util.*;

//  77. Combinations
//  https://leetcode.com/problems/combinations/description/
//  http://www.lintcode.com/zh-cn/problem/combinations/
public class _077_BackTracking_Combinations_M {
    //A short recursive Java solution based on C(n,k)=C(n-1,k-1)+C(n-1,k)
/*    Basically, this solution follows the idea of the mathematical formula C(n,k)=C(n-1,k-1)+C(n-1,k).

    Here C(n,k) is divided into two situations. Situation one, number n is selected, so we only need to select k-1 from n-1 next. Situation two, number n is not selected, and the rest job is selecting k from n-1.*/
    public List<List<Integer>> combine01(int n, int k) {
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = this.combine01(n - 1, k - 1);
        result.forEach(e -> e.add(n));
        result.addAll(this.combine01(n - 1, k));
        return result;
    }

    @Test
    public void test01(){
/*        组给出两个整数n和k，返回从1......n中选出的k个数的组合。

        样例
        例如 n = 4 且 k = 2*/
        System.out.println(combine01(4,2));
    }//[[3, 4], [2, 4], [1, 4], [2, 3], [1, 3], [1, 2]]

//------------------------------------------------------------------------------////////
    //Backtracking Solution Java
    public List<List<Integer>> combine02(int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combine02(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }
    public void combine02(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if(k==0) {
            combs.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=start;i<=n;i++) {
            comb.add(i);
            combine02(combs, comb, i+1, n, k-1);
            comb.remove(comb.size()-1);
        }
    }
    @Test
    public void test02(){
/*        组给出两个整数n和k，返回从1......n中选出的k个数的组合。

        样例
        例如 n = 4 且 k = 2*/
        System.out.println(combine02(4,2));
    }//[[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
//------------------------------------------------------------------------------////////
    //DP for the problem
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

    public List<List<Integer>> combine03(int n, int k)
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
    @Test
    public void test03(){
/*        组给出两个整数n和k，返回从1......n中选出的k个数的组合。

        样例
        例如 n = 4 且 k = 2*/
        System.out.println(combine03(4,2));
    }
//------------------------------------------------------------------------------////////
    //3 ms Java Solution
    public List<List<Integer>> combine04(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k > n || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        result = combine04(n - 1, k - 1);
        for (List<Integer> list : result) {
            list.add(n);
        }
        result.addAll(combine04(n - 1, k));
        return result;
    }
    @Test
    public void test04(){
/*        组给出两个整数n和k，返回从1......n中选出的k个数的组合。

        样例
        例如 n = 4 且 k = 2*/
        System.out.println(combine04(4,2));
    }
//------------------------------------------------------------------------------////////

    //DFS recursive Java Solution
    //A DFS idea with back-trace. Very straightforward.
    public List<List<Integer>> combine05(int n, int k) {
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
    @Test
    public void test05(){
/*        组给出两个整数n和k，返回从1......n中选出的k个数的组合。

        样例
        例如 n = 4 且 k = 2*/
        System.out.println(combine05(4,2));
    }

//-----------------------------------------------------------------------------

    // 9Ch  最好还是这个
    public List<List<Integer>> combine_J(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();

        helper(result, solution, n, k, 1);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> solution,
                        int n, int k, int start) {
        if (solution.size() == k){
            result.add(new ArrayList(solution));
            return;
        }

        for(int i = start; i<= n; i++){
            solution.add(i);
            // the new start should be after the next number after i
            helper(result, solution, n, k, i + 1);
            solution.remove(solution.size() - 1);
        }
    }
    @Test
    public void test06(){
/*        组给出两个整数n和k，返回从1......n中选出的k个数的组合。
        例如 n = 4 且 k = 2*/
        System.out.println(combine_J(4,2));
    }//[[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]

//-----------------------------------------------------------------------------
}

/*  lint

组合

组给出两个整数n和k，返回从1......n中选出的k个数的组合。

样例
例如 n = 4 且 k = 2

返回的解为：

[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]

标签
数组 回溯法
相关题目
困难 添加运算符 27 %
中等 N皇后问题 II 41 %
中等 N皇后问题

 */


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

