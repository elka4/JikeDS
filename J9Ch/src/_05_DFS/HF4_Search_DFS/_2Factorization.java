package _05_DFS.HF4_Search_DFS;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//Factorization
public class _2Factorization {
    /**
     * @param n an integer
     * @return a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        // write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), n, 2);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> item, int n, int start) {
        if (n <= 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<Integer>(item));
            }
            return;
        }

        for (int i = start; i <= Math.sqrt(n); ++i) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n / i, i);
                item.remove(item.size()-1);
            }
        }
        if (n >= start) {
            item.add(n);
            helper(result, item, 1, n);
            item.remove(item.size() - 1);
        }
    }


//---------------------------------///////////////////////

    // version: 高频题班
    /**
     * @param n an integer
     * @return a list of combination
     */

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    void dfs(int start, int remain) {
        if (remain == 1) {
            if (path.size() != 1) {
                ans.add(new ArrayList<>(path)); //deep copy
            }
            return;
        }

        for (int i = start; i <= remain; i++) {
            if (i > remain / i) {
                break;
            }
            if (remain % i == 0) {
                path.add(i);                  //进栈
                dfs(i, remain / i);
                path.remove(path.size() - 1); //出栈
            }
        }

        path.add(remain);
        dfs(remain, 1);
        path.remove(path.size() - 1);
    }

    public List<List<Integer>> getFactors2(int n) {
        // write your code here
        dfs(2, n);
        return ans;
    }

    @Test
    public void test02(){
        System.out.println(getFactors2(8));
    }
//---------------------------------///////////////////////
}
/*
A non-negative numbers can be regarded as product of its factors.
Write a function that takes an integer n and return all possible combinations of its factors.

 Notice

Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combination.
Have you met this question in a real interview? Yes
Example
Given n = 8
return [[2,2,2],[2,4]]
// 8 = 2 x 2 x 2 = 2 x 4.

Given n = 1
return []

Given n = 12
return [[2,6],[2,2,3],[3,4]]
 */