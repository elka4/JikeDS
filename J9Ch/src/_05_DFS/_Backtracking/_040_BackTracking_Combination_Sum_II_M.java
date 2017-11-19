package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _040_BackTracking_Combination_Sum_II_M {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }



    class Solution{
        public List<List<Integer>> combinationSum2(int[] cand, int target) {
            Arrays.sort(cand);
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> path = new ArrayList<Integer>();
            dfs_com(cand, 0, target, path, res);
            return res;
        }
        void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
            if (target == 0) {
                res.add(new ArrayList(path));
                return ;
            }
            if (target < 0) return;
            for (int i = cur; i < cand.length; i++){
                if (i > cur && cand[i] == cand[i-1]) continue;
                path.add(path.size(), cand[i]);
                dfs_com(cand, i+1, target - cand[i], path, res);
                path.remove(path.size()-1);
            }
        }
    }

//-------------------------------------------------------------------------////////////////////
    //jiuzhang
    public class Jiuzhang {
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
                helper(candidates, i + 1, combination, target - candidates[i], results);
                combination.remove(combination.size() - 1);
            }
        }
    }



//-------------------------------------------------------------------------////////////////////





//-------------------------------------------------------------------------////////////////////






}
/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

 */


/*
lint

给出一组候选数字(C)和目标数字(T),找出C中所有的组合，使组合中数字的和为T。C中每个数字在每个组合中只能使用一次。
注意事项

    所有的数字(包括目标数字)均为正整数。
    元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
    解集不能包含重复的组合。

您在真实的面试中是否遇到过这个题？
样例

给出一个例子，候选数字集合为[10,1,6,7,2,1,5] 和目标数字 8  ,

解集为：[[1,7],[1,2,5],[2,6],[1,1,6]]

 */