package _01_Array.Other_dbf_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
LeetCode – Combination Sum II (Java)

Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.
Each number in C may only be used ONCE in the combination.

Note:
1) All numbers (including target) will be positive integers.
2) Elements in a combination (a1, a2, … , ak) must be in non-descending order.
(ie, a1 ≤ a2 ≤ … ≤ ak).
3) The solution set must not contain duplicate combinations.

Java Solution

This problem is an extension of Combination Sum.
The difference is one number in the array can only be used ONCE.
 */


public class Combination_SumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper(result, curr, 0, target, candidates);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> curr,
                       int start, int target, int[] candidates){

        if(target==0){
            result.add(new ArrayList<Integer>(curr));
            return;
        }

        if(target<0){
            return;
        }

        int prev=-1;
        for(int i=start; i<candidates.length; i++){
            // each time start from different element
            if(prev!=candidates[i]){
                curr.add(candidates[i]);
                // and use next element only
                helper(result, curr, i+1, target-candidates[i], candidates);
                curr.remove(curr.size()-1);
                prev=candidates[i];
            }
        }
    }



//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






}
