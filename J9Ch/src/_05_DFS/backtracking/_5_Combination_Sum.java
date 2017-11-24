package _05_DFS.backtracking;

import org.junit.Test;

import java.util.*;

//https://leetcode.com/problems/permutations/#/discuss


//
public class _5_Combination_Sum {
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

    //Combination Sum : https://leetcode.com/problems/combination-sum/

    public List<List<Integer>> combinationSum(int[] nums, int target) {
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
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backtrack(list, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    @Test
    public void test01(){
        int[] nums = {2, 3, 6, 7};
        System.out.println(combinationSum(nums, 7));
    }

//------------------------------------------------------------------------------//////
//Iterative Java DP solution

/*
The main idea reminds an approach for solving coins/knapsack problem - to store
the result for all i < target and create the solution from them. For that for each
 t from 1 to our target we try every candidate which is less or equal to t in ascending
 order. For each candidate "c" we run through all combinations for target t-c starting
 with the value greater or equal than c to avoid duplicates and store only ordered
  combinations.

 */

    public List<List<Integer>> combinationSum2(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { // run through all targets from 1 to t
            List<List<Integer>> newList = new ArrayList(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                    // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }


//------------------------------------------------------------------------------//////
//Java solution using recursive
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);

        return result;
    }

    private void getResult(List<List<Integer>> result, List<Integer> cur,
                           int candidates[], int target, int start){
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



//------------------------------------------------------------------------------//////
//From large to small might be easier and shorter.

    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new LinkedList<Integer>(), candidates, target);
        return result;
    }
    private void dfs(List<List<Integer>> result, LinkedList<Integer> list,
                     int[] arr, int target) {
        if (target == 0) {
            result.add(new LinkedList<Integer>(list));
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                list.addFirst(arr[i]);
                dfs(result, list, Arrays.copyOfRange(arr, 0, i + 1),
                        target - arr[i]);
                list.removeFirst();
            }
        }
    }

//------------------------------------------------------------------------------//////
//A little revise without using Arrays.sort which will run faster.

    public List<List<Integer>> combinationSum5(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        combinationSum(result,new ArrayList<Integer>(),candidates,target,0);
        return result;
    }
    public void combinationSum(List<List<Integer>> result, List<Integer> cur,
                               int[] candidates, int target,int start) {
        if (target > 0) {
            // not using the condition "target >= candidates[i]"
            for (int i = start;i < candidates.length;i++) {
                cur.add(candidates[i]);
                combinationSum(result, cur, candidates, target-candidates[i],i);
                cur.remove(cur.size() - 1);
            }
        }
        if (target == 0) result.add(new ArrayList<Integer>(cur));
    }
//------------------------------------------------------------------------------//////
//A solution avoid using set
    /*
    Sort the candidates and we choose from small to large recursively,
    every time we add a candidate to our possible sub result,
    we subtract the target to a new smaller one.
     */

    public List<List<Integer>> combinationSum6(int[] candidates, int target) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Arrays.sort(candidates); // sort the candidates
        // collect possible candidates from small to large to eliminate duplicates,
        recurse(new ArrayList<Integer>(), target, candidates, 0, ret);
        return ret;
    }

    // the index here means we are allowed to choose candidates from that index
    private void recurse(List<Integer> list, int target, int[] candidates,
                         int index, List<List<Integer>> ret) {
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

//------------------------------------------------------------------------------//////

    //Easy to understand 96% performance Java solution


    public List<List<Integer>> combinationSum7(int[] candidates, int target) {
        return combinationSum(candidates, target, 0);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, int start) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <target) {
                for (List<Integer> ar : combinationSum(candidates,
                        target - candidates[i], i)) {
                    ar.add(0, candidates[i]);
                    res.add(ar);
                }
            } else if (candidates[i] == target) {
                List<Integer> lst = new ArrayList<>();
                lst.add(candidates[i]);
                res.add(lst);
            } else
                break;
        }
        return res;
    }
//------------------------------------------------------------------------------//////
//Share my 15-line DFS Java code
    public List<List<Integer>> combinationSum8(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(ans, new ArrayList<Integer>(), candidates, target, 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list,
                     int[] cand, int remain, int from) {
        if (remain < 0) { return; }
        if (remain == 0) { ans.add(new ArrayList<Integer>(list)); return; }
        //cand[] sorted; from is the starting point of picking elements at this level
        for (int i=from; i<cand.length; ++i) {
            list.add(cand[i]);
            dfs(ans, list, cand, remain-cand[i], i);
            list.remove(list.size()-1);
        }
    }


//------------------------------------------------------------------------------//////
//Java solution (backtracking)



    public class Solution {

        private List<List<Integer>> solution;

        private List<Integer> curSolution;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            solution = new ArrayList<List<Integer>>();
            curSolution = new ArrayList<Integer>();
            Arrays.sort(candidates);
            backTrack(candidates, target, 0);
            return solution;
        }

        private void backTrack(int[] candidates, int target, int lastIdx) {
            if (target == 0) {
                solution.add(new ArrayList<>(curSolution));
            }
            else if (target < 0) {
                return;
            }
            else {
                int i = lastIdx;
                while (i < candidates.length) {
                    int candidate = candidates[i];
                    curSolution.add(candidate);
                    backTrack(candidates, target - candidate, i);
                    curSolution.remove(curSolution.size() - 1);
                    while (i < candidates.length && candidates[i] == candidate) {
                        i++;
                    }
                }
            }
        }
    }
//------------------------------------------------------------------------------//////
    //Non-Recursive JAVA solution
public List<List<Integer>> combinationSum10(int[] candidates, int target) {
    Arrays.sort(candidates);
    int i=0, size = candidates.length, sum=0;
    Stack<Integer> combi = new Stack<>(), indices = new Stack<>();
    List<List<Integer>> result = new ArrayList<>();
    while (i < size) {
        if (sum + candidates[i]>= target) {
            if (sum + candidates[i] == target) {
                combi.push(candidates[i]);
                result.add(new ArrayList<>(combi));
                combi.pop();
            }
            // indices stack and combination stack should have the s
            // ame size all the time
            if (!indices.empty()){
                sum -= combi.pop();
                i = indices.pop();
                while (i == size-1 && !indices.empty()) {
                    i = indices.pop();
                    sum -= combi.pop();

                }
            }
            i++;
        } else {
            combi.push(candidates[i]);
            sum +=candidates[i];
            indices.push(i);
        }
    }
    return result;
}

//------------------------------------------------------------------------------//////


    //Simple java solution

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum11 (int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<Integer>());
        return res;
    }

    private void helper(int[] can, int start, int target,List<Integer> each) {
        for (int i = start; i < can.length; i++) {
            List<Integer> temp = new ArrayList<>(each);
            if (can[i] == target) {
                temp.add(can[i]);
                res.add(temp);
                break;
            } else if (can[i] < target) {
                temp.add(can[i]);
                helper(can, i, target - can[i], new ArrayList<>(temp));
            } else {break;}
        }
        return;
    }


//------------------------------------------------------------------------------///////

    /*
    Nice Solution! I solved this problem with the same idea. However,
    instead of using res as a field, i made it as a local variable in my program:

if candidates[i] == target, add it and return;

if candidates[i] > target, it means we can break this loop since no combination
starting from this element could sum up to target;

if candidates[i] < target, we can recursively find the solution starting from the
same element but different target number (which is current target - candidates[i]).


     */

    public class Solution3 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (candidates == null || candidates.length == 0) return result;
            Arrays.sort(candidates);
            return combinationSum(candidates, target, 0);
        }

        private List<List<Integer>> combinationSum(int[] candidates,
                                                   int target, int start) {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(candidates[i]);
                    result.add(temp);
                    return result;
                }
                if (candidates[i] > target) return result;
                List<List<Integer>> next = combinationSum(candidates,
                        target - candidates[i], i);
                if (next.size() == 0) continue;
                for (List<Integer> list : next) list.add(0, candidates[i]);
                result.addAll(next);
            }
            return result;
        }
    }


//------------------------------------------------------------------------------///////
//https://discuss.leetcode.com/topic/7277/a-recursive-yet-efficient-java-solution-with-explanation
public class Solution4 {
    private List<List<Integer>> res = new LinkedList<List<Integer>>();

    private void combinationSum(int [] candidates, LinkedList<Integer> vec,
                                int start, int target){

        for(int i=start; i<candidates.length; ++i){
            if(candidates[i] < target){
                LinkedList<Integer> newVec = new LinkedList<Integer>();
                newVec.addAll(vec);
                newVec.add(candidates[i]);

                // Try a new combination, one could repeat itself again,
                // so start from "i", instead of "i+1"
                combinationSum(candidates, newVec, i, target-candidates[i]);
            }else if(candidates[i] == target){

                // Found a combination
                LinkedList<Integer> newVec = new LinkedList<Integer>();
                newVec.addAll(vec);
                newVec.add(candidates[i]);
                res.add(newVec);
            }else{
                // candidates[i] > target, no need to continue
                break;
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        LinkedList<Integer> vec = new LinkedList<Integer>();
        this.combinationSum(candidates, vec, 0, target);
        return res;
    }
}



//------------------------------------------------------------------------------///////
//Java Backtracking Solution, Easy to understand

    public List<List<Integer>> combinationSum12(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        add(res, new ArrayList<Integer>(), candidates, 0, target);
        return res;
    }

    private void add(ArrayList<List<Integer>> res, ArrayList<Integer> list,
                     int[] candidates, int start, int target){
        if(target < 0)  return;
        else if(target == 0){
            res.add(list);
            return;
        }
        for(int i=start; i<candidates.length; ++i){
            ArrayList<Integer> temp = new ArrayList<Integer>(list);
            temp.add(candidates[i]);
            add(res, temp, candidates, i, target-candidates[i]);
        }
    }

//------------------------------------------------------------------------------///////


//------------------------------------------------------------------------------///////

}
