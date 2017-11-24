package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//  47. Permutations II
public class _047_BackTracking_Permutations_II_M {
//    Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }



/*    Really easy Java solution, much easier than the solutions with very high vote
    Use an extra boolean array " boolean[] used" to indicate whether the value is added to list.

    Sort the array "int[] nums" to make sure we can skip the same value.

    when a number has the same value with its previous, we can use this number only if his previous is used*/

    public class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if(nums==null || nums.length==0) return res;
            boolean[] used = new boolean[nums.length];
            List<Integer> list = new ArrayList<Integer>();
            Arrays.sort(nums);
            dfs(nums, used, list, res);
            return res;
        }

        public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
            if(list.size()==nums.length){
                res.add(new ArrayList<Integer>(list));
                return;
            }
            for(int i=0;i<nums.length;i++){
                if(used[i]) continue;
                if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
                used[i]=true;
                list.add(nums[i]);
                dfs(nums,used,list,res);
                used[i]=false;
                list.remove(list.size()-1);
            }
        }
    }


//    Share my Java code with detailed explanantion
    public class Solution2 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums==null || nums.length==0) { return ans; }
            permute(ans, nums, 0);
            return ans;
        }

        private void permute(List<List<Integer>> ans, int[] nums, int index) {
            if (index == nums.length) {
                List<Integer> temp = new ArrayList<>();
                for (int num: nums) { temp.add(num); }
                ans.add(temp);
                return;
            }
            Set<Integer> appeared = new HashSet<>();
            for (int i=index; i<nums.length; ++i) {
                if (appeared.add(nums[i])) {
                    swap(nums, index, i);
                    permute(ans, nums, index+1);
                    swap(nums, index, i);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int save = nums[i];
            nums[i] = nums[j];
            nums[j] = save;
        }
    }

//------------------------------------------------------------------------------/
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
                helper(candidates, i, target - candidates[i], combination, result);
                combination.remove(combination.size() - 1);
            }
        }
    }

//------------------------------------------------------------------------------/


//------------------------------------------------------------------------------/



}
/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */