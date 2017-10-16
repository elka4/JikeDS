package DP._s_6_DP2;

import java.util.ArrayList;

public class _8k_SumII {
/**
 * @param A: an integer array.
 * @param k: a positive integer (k <= length(A))
 * @param target: a integer
 * @return an integer
 */    
ArrayList<ArrayList<Integer> > ans;
public void dfs(int A[], int K, int target, int index, ArrayList<Integer> tans)
{

    if(K == 0 && target == 0) {
        ans.add(new ArrayList<Integer>(tans));
        return ;
    }
    if(K < 0 || target < 0 || index < 0)
        return ;
    dfs(A, K, target, index - 1, tans);
    tans.add(A[index]);
    dfs(A, K  - 1, target - A[index], index - 1, tans);
    tans.remove(tans.size() - 1);
    
}
public ArrayList<ArrayList<Integer>> kSumII(int A[], int K, int target) {
    ans = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> tans = new ArrayList<Integer>();
    dfs(A, K, target, A.length - 1, tans);
    return ans;
}
}
/*Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.

Tags 
LintCode Copyright Dynamic Programming
Related Problems 
Medium k Sum II 33 %*/