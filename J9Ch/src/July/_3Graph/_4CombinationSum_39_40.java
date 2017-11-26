package July._3Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**135. Combination Sum
 * Medium
 C中的数字可以无限制重复被选取。
 * Created by tianhuizhu on 6/27/17.
 */


public class _4CombinationSum_39_40 {
    // version 1: Remove duplicates & generate a new array

    /**
     * @param candidates: A list of integers
     * @param target:An   integer
     * @return: A list of lists of integers
     */



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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }

        int[] nums = removeDuplicates(candidates);

        dfs(nums, 0, new ArrayList<Integer>(), target, results);

        return results;
    }

    /*
    Given candidate set [2,3,6,7] and target 7, a solution set is:

    [7]
    [2, 2, 3]
     */
    @Test
    public void test01() {
        //combinationSum(int[] candidates, int target)
        int[] candidates = {2, 3, 6, 7};
        System.out.println(combinationSum(candidates, 7));
        //  [[2, 2, 3], [7]]
    }

    @Test
    public void test02() {
        //combinationSum(int[] candidates, int target)
        int[] candidates = {2, 2, 2, 3, 6, 7};
        System.out.println(combinationSum(candidates, 7));

    }

    @Test
    public void testRemoveDup() {
        //combinationSum(int[] candidates, int target)
        int[] candidates = {2, 2, 2, 3, 6, 7};
        int[] result = removeDuplicates(candidates);
        for (int i : result) {
            System.out.print(i + " ");
        }

    }
//-------------------------------------------------------------------------------

    // version 2: reuse candidates array
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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

    @Test
    public void test2() {
        //combinationSum(int[] candidates, int target)
        int[] candidates = {2, 3, 6, 7};
        System.out.println(combinationSum(candidates, 7));
    }

//-------------------------------------------------------------------------------



//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------
}
/*
combination sum 1
给出一组候选数字(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字可以无限制重复被选取。

例如,给出候选数组[2,3,6,7]和目标数字7，所求的解为：

[7]，

[2,2,3]

 注意事项

所有的数字(包括目标数字)均为正整数。
元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
解集不能包含重复的组合。
样例
给出候选数组[2,3,6,7]和目标数字7

返回 [[7],[2,2,3]]


 */


/*
combination sum 2

给出一组候选数字(C)和目标数字(T),找出C中所有的组合，使组合中数字的和为T。C中每个数字在每个组合中只能使用一次。

 注意事项

所有的数字(包括目标数字)均为正整数。
元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
解集不能包含重复的组合。
样例
给出一个例子，候选数字集合为[10,1,6,7,2,1,5] 和目标数字 8  ,

解集为：[[1,7],[1,2,5],[2,6],[1,1,6]]
 */