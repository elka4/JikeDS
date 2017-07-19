package J_5_Depth_First_Search.other.backtracking;
import java.util.*;

//https://leetcode.com/problems/permutations/#/discuss


/*
Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

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
public class _6_Combination_Sum_II {
    //Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList,
                           int [] nums, int remain, int start){
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

////////////////////////////////////////////////////////////////////////////////////
//Java solution using dfs, easy understand

    public List<List<Integer>> combinationSum2_2(int[] cand, int target) {
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
////////////////////////////////////////////////////////////////////////////////////
//Combination Sum I, II and III Java solution (see the similarities yourself)
class solution1 {
    //Combination Sum I

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand, int remain, int start) {
        if (remain < 0) return; /** no solution */
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < cand.length; i++) {
                tempList.add(cand[i]);
                backtrack(list, tempList, cand, remain - cand[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }

    }
}

    class solution2 {

        //Combination Sum II

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> list = new LinkedList<List<Integer>>();
            Arrays.sort(candidates);
            backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
            return list;
        }

        private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand, int remain, int start) {

            if (remain < 0) return; /** no solution */
            else if (remain == 0) list.add(new ArrayList<>(tempList));
            else {
                for (int i = start; i < cand.length; i++) {
                    if (i > start && cand[i] == cand[i - 1]) continue; /** skip duplicates */
                    tempList.add(cand[i]);
                    backtrack(list, tempList, cand, remain - cand[i], i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    class solution3 {

        //Combination Sum III

        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> list = new ArrayList<>();
            backtrack(list, new ArrayList<Integer>(), k, n, 1);
            return list;
        }

        private void backtrack(List<List<Integer>> list, List<Integer> tempList, int k, int remain, int start) {
            if (tempList.size() > k) return; /** no solution */
            else if (tempList.size() == k && remain == 0) list.add(new ArrayList<>(tempList));
            else {
                for (int i = start; i <= 9; i++) {
                    tempList.add(i);
                    backtrack(list, tempList, k, remain - i, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
}
////////////////////////////////////////////////////////////////////////////////////
//Java - short and recursive, clean code.


    public List<List<Integer>> combinationSum2_3(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        Arrays.sort(candidates); // need sort to make this work.
        combination(candidates, target, 0, comb, ans);
        return ans;
    }

    private void combination(int[] candi, int target, int start,
                             List<Integer> comb, List<List<Integer>> ans) {
        for (int i = start; i < candi.length; i++) {
            if (i > start && candi[i] == candi[i - 1]) //remove duplicates.
                continue;
            if (candi[i] == target) {
                //recursion exit.
                List<Integer> newComb = new ArrayList<>(comb);
                newComb.add(candi[i]);
                ans.add(newComb);
            } else if (candi[i] < target) {
                //continue to look for the rest.
                List<Integer> newComb = new ArrayList<>(comb);
                newComb.add(candi[i]);
                combination(candi, target - candi[i], i + 1, newComb, ans);
            } else
                break; //invalid path, return nothing.
        }
    }
////////////////////////////////////////////////////////////////////////////////////
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> combos = new LinkedList<>();
        if (num == null || num.length == 0) return combos;
        Arrays.sort(num);
        combinationSum(num, target, new LinkedList<Integer>(), 0, combos);
        return combos;
    }

    private void combinationSum(int[] num, int target, List<Integer> list, int start, List<List<Integer>> combos) {
        if (target == 0) {
            combos.add(list);
        } else {
            int prev = 0;
            for (int i = start; i < num.length; i++) {
                if (num[i] == prev) continue;
                int nextTarget = target - num[i];
                if (nextTarget >= 0) {
                    List<Integer> copy = new LinkedList<>(list);
                    copy.add(num[i]);
                    combinationSum(num, nextTarget, copy, i + 1, combos);
                } else {
                    break;
                }
                prev = num[i];
            }
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////
//my version, similar, I think I ,managed to remove some of the code in the recursive find ,
// above the comment line, so the result looks cleaner

    public class Solution5 {
        List<Integer> partialSolution ;
        List<List<Integer>> result = new  ArrayList<List<Integer>>();
        public List<List<Integer>> combinationSum2(int[] num, int target) {
            partialSolution = new ArrayList<Integer>();
            Arrays.sort(num);

            recursive(num,0, target);

            return new ArrayList<List<Integer>>(result);
        }

        private void recursive(int[] candidates,int low, int target) {
            if ( target == 0) {
                List<Integer> elem = new ArrayList<Integer>(partialSolution);
                result.add(elem);
                return;
            } else if ( target < 0) {
                return;
            }

            for(int i=low;i<candidates.length;i++) {
                if (i > low && candidates[i] == candidates[i-1]) continue;
                partialSolution.add(candidates[i]);
                recursive(candidates,  i+1, target - candidates[i]);
                partialSolution.remove(partialSolution.size()-1);
            }


        }
    }

/////////////////////////////////////////////////////////////////////////////

//    Java solutions beats 99,87%

    public class Solution6 {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> results = new ArrayList<>();
            calcCombinationSum2(candidates, 0,
                    new int[candidates.length], 0, target, results);
            return results;
        }

        private void calcCombinationSum2(int[] candidates, int cindex, int[] list,
                                 int lindex, int target, List<List<Integer>> results) {
            if (target == 0) {
                List<Integer> result = new ArrayList<>();
                for (int i = 0; i < lindex; i++) {
                    result.add(list[i]);
                }
                results.add(result);
                return;
            }

            int prev = 0;
            for (int i = cindex; i < candidates.length; i++) {
                if (candidates[i] != prev) {
                    if (target - candidates[i] < 0) {
                        break;
                    }

                    list[lindex] = candidates[i];
                    calcCombinationSum2(candidates, i + 1, list, lindex + 1,
                            target - candidates[i], results);
                    prev = candidates[i];
                }
            }
        }
    }

/////////////////////////////////////////////////////////////////////

    //Most people who use recursive backtrack to solve this question have code like:


    /*
    if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            if (i > post && candidates[i] == candidats[i - 1])
              continue;
            }
            list.add(candidates[i]);
            helper(candidates, target - candidates[i], res, list, i + 1);
            list.remove(list.size() - 1);

     */

    /*
    his is absolutely correct. However, we can do much more pruning by breaking much earlier.

For example: the list is [1, 1, 2, 5, 6, 7, 10], target is 8 and the current list is [1, 1, 2]. Now we are at 5, and we know that [1, 1, 2, 5] will be greater than 8. The next to check is [1, 1, 2, 6]. However, we should already know that [1, 1, 2, 6] cannot work since [1, 1, 2, 5] already has a sum larger than 8. There is no need to check for [1, 1, 2, 6] or [1, 1, 2, 7] and so no.

Thus, when we find a match or the current sum is already larger than the target, we should not continue with the current list.

The code is something like:


     */

    private boolean helper(int[] candidates, int target, List<List<Integer>> res,
                           List<Integer> list, int pos) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return true;
        }

        if (target < 0) {
            return true;
        }

        for (int i = pos; i < candidates.length; i++) {
            if (i > pos && candidates[i] == candidates[i - 1]) {
                continue;
            }

            list.add(candidates[i]);
            boolean con = helper(candidates, target - candidates[i], res, list, i + 1);
            list.remove(list.size() - 1);
            if(con) {
                break;
            }
        }

        return false;
    }

////////////////////////////////////////////////////////////////////////////


    //5ms JAVA solution
    public List<List<Integer>> combinationSum2_7(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans=new ArrayList<List<Integer>>();
        ch(candidates,target,0,new ArrayList<Integer>(),ans);
        return ans;
    }
    public void ch(int[] candidates,int remain,int rindex,List<Integer> tmp,List<List<Integer>> ans)
    {
        if(remain==0)
        {
            List<Integer> a=new ArrayList<Integer>(tmp);
            ans.add(a);
            return;
        }
        int entered=0; // get rid of duplicate combinations
        for(int i=rindex;i<candidates.length;i++)
        {
            if(entered!=candidates[i]) // get rid of duplicate combinations
            {
                if(remain-candidates[i]<0) break; //This line of code can reduce 7ms from execution time!
                tmp.add(candidates[i]);
                entered=candidates[i];
                ch(candidates,remain-candidates[i],i+1,tmp,ans);
                tmp.remove(tmp.size()-1);
            }
        }
    }




}
