package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _039_BackTracking_Combination_Sum_M {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
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
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    class Solution{
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ret = new LinkedList<List<Integer>>();
            Arrays.sort(candidates); // sort the candidates
            // collect possible candidates from small to large to eliminate duplicates,
            recurse(new ArrayList<Integer>(), target, candidates, 0, ret);
            return ret;
        }

        // the index here means we are allowed to choose candidates from that index
        private void recurse(List<Integer> list, int target, int[] candidates, int index, List<List<Integer>> ret) {
            if (target == 0) {
                ret.add(list);
                return;
            }
            for (int i = index; i < candidates.length; i++) {
                int newTarget = target - candidates[i];
                if (newTarget >= 0) {
                    List<Integer> copy = new ArrayList<Integer>(list);
                    copy.add(candidates[i]);
                    recurse(copy, newTarget, candidates, i, ret);
                } else {
                    break;
                }
            }
        }
    }

    public class Solution2 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            getResult(result, new ArrayList<Integer>(), candidates, target, 0);

            return result;
        }

        private void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start){
            if(target > 0){
                for(int i = start; i < candidates.length && target >= candidates[i]; i++){
                    cur.add(candidates[i]);
                    getResult(result, cur, candidates, target - candidates[i], i);
                    cur.remove(cur.size() - 1);
                }//for
            }//if
            else if(target == 0 ){
                result.add(new ArrayList<Integer>(cur));
            }//else if
        }
    }


    public class Solution3 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, new LinkedList<Integer>(), candidates, target);
            return result;
        }
        private void dfs(List<List<Integer>> result, LinkedList<Integer> list, int[] arr, int target) {
            if (target == 0) {
                result.add(new LinkedList<Integer>(list));
                return;
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] <= target) {
                    list.addFirst(arr[i]);
                    dfs(result, list, Arrays.copyOfRange(arr, 0, i + 1), target - arr[i]);
                    list.removeFirst();
                }
            }
        }
    }

//------------------------------------------------------------------------------
    // 9Ch
    // version 1: Remove duplicates & generate a new array
    public class Jiuzhang1 {
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
    }

    // version 2: reuse candidates array
    // combination在存储状态
    public class Jiuzhang2 {
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
                // i和i+1， 是I 和 II的唯一区别
                helper(candidates, i, target - candidates[i], combination, result);
                combination.remove(combination.size() - 1);
            }
        }
    }

//------------------------------------------------------------------------------

}
/*
Given a set of candidate numbers (C) (without duplicates) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:
[
  [7],
  [2, 2, 3]
]
 */

/*
lint


给出一组候选数字(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字可以无限制重复被选取。

例如,给出候选数组[2,3,6,7]和目标数字7，所求的解为：

[7]，

[2,2,3]
注意事项

    所有的数字(包括目标数字)均为正整数。
    元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
    解集不能包含重复的组合。

您在真实的面试中是否遇到过这个题？
样例

给出候选数组[2,3,6,7]和目标数字7

返回 [[7],[2,2,3]]

 */