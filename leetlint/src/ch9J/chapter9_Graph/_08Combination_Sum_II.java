package ch9J.chapter9_Graph;

import java.util.ArrayList;

public class _08Combination_Sum_II {
private ArrayList<ArrayList<Integer>> results;

public ArrayList<ArrayList<Integer>> combinationSum2(
		int[] candidates, int target) {
    if (candidates.length < 1) {
        return results;
    }

    ArrayList<Integer> path = new ArrayList<Integer>();
    java.util.Arrays.sort(candidates);
    results = new ArrayList<ArrayList<Integer>> ();
    combinationSumHelper(path, candidates, target, 0);

    return results;
}

private void combinationSumHelper(ArrayList<Integer> path, 
							int[] candidates, int sum, int pos) {
    if (sum == 0) {
        results.add(new ArrayList<Integer>(path));
    }

    if (pos >= candidates.length || sum < 0) {
        return;
    }

    int prev = -1;
    for (int i = pos; i < candidates.length; i++) {
        if (candidates[i] != prev) {
            path.add(candidates[i]);
            combinationSumHelper(path, candidates, 
            		sum - candidates[i], i + 1);
            prev = candidates[i];
            path.remove(path.size()-1);
        }
    }
}
}

/*Given a collection of candidate numbers (C) and a target number 
 * (T), find all unique combinations in C where the candidate 
 * numbers sums to T.
 

Each number in C may only be used once in the combination.

 Notice

All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, É , ak) must be in 
non-descending order. (ie, a1 ² a2 ² É ² ak).
The solution set must not contain duplicate combinations.
Have you met this question in a real interview? Yes
Example
Given candidate set [10,1,6,7,2,1,5] and target 8,

A solution set is:

[
  [1,7],
  [1,2,5],
  [2,6],
  [1,1,6]
]
Tags 
Backtracking Array Depth First Search
Related Problems 
Medium Combination Sum 28 %*/