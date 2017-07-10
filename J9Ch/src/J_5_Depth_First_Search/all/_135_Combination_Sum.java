package J_5_Depth_First_Search.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**135. Combination Sum
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _135_Combination_Sum {

    // version 1: Remove duplicates & generate a new array
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }

        int[] nums = removeDuplicates(candidates);

        dfs(nums, 0, new ArrayList<Integer>(), target, results);

        return results;
    }

    private int[] removeDuplicates(int[] candidates) {
        Arrays.sort(candidates);

        int index = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != candidates[index]) {
                candidates[++index] = candidates[i];
            }
        }

        int[] nums = new int[index + 1];
        for (int i = 0; i < index + 1; i++) {
            nums[i] = candidates[i];
        }

        return nums;
    }

    private void dfs(int[] nums,
                     int startIndex,
                     List<Integer> combination,
                     int remainTarget,
                     List<List<Integer>> results) {
        if (remainTarget == 0) {
            results.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (remainTarget < nums[i]) {
                break;
            }
            combination.add(nums[i]);
            dfs(nums, i, combination, remainTarget - nums[i], results);
            combination.remove(combination.size() - 1);
        }
    }

/*
Given candidate set [2,3,6,7] and target 7, a solution set is:

[7]
[2, 2, 3]
 */
    @Test
    public void test(){
        //combinationSum(int[] candidates, int target)
        int[] candidates = {2,3,6,7};
        System.out.println(combinationSum(candidates, 7));
    }




    // version 2: reuse candidates array
    public class Solution2 {
        public  List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (candidates == null) {
                return result;
            }

            List<Integer> combination = new ArrayList<>();
            Arrays.sort(candidates);
            helper(candidates, 0, target, combination, result);

            return result;
        }

        void helper(int[] candidates,
                    int index,
                    int target,
                    List<Integer> combination,
                    List<List<Integer>> result) {
            if (target == 0) {
                result.add(new ArrayList<Integer>(combination));
                return;
            }

            for (int i = index; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    break;
                }

                if (i != index && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                combination.add(candidates[i]);
                helper(candidates, i, target - candidates[i], combination, result);
                combination.remove(combination.size() - 1);
            }
        }
    }
}
