package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 153. Combination Sum II
 * Medium
 C中每个数字在每个组合中只能使用一次。
 * Created by tianhuizhu on 6/27/17.
 */

/*
Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order.
(ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
 */

//
public class _153_Combination_Sum_II {
    /**
     * @param candidates: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] candidates,
                                               int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }

        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<Integer>();
        helper(candidates, 0, combination, target, results);

        return results;
    }

    private void helper(int[] candidates,
                        int startIndex,
                        List<Integer> combination,
                        int target,
                        List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (i != startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target < candidates[i]) {
                break;
            }

            combination.add(candidates[i]);

            helper(candidates, i + 1, combination,
                    target - candidates[i], results);

            combination.remove(combination.size() - 1);
        }
    }


    /*
    Given candidate set [10,1,6,7,2,1,5] and target 8,

A solution set is:

[
  [1,7],
  [1,2,5],
  [2,6],
  [1,1,6]
]
     */

    @Test
    public void test01(){
        //combinationSum2(int[] candidates, int target)
        int[] candidates = {10,1,6,7,2,1,5};
        System.out.println(combinationSum2(candidates, 8));
    }

}
