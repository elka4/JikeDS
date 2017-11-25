package _05_DFS.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/permutations/#/discuss


public class _2_Subsets_II {

//Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/

    /*
Given a collection of integers that might contain duplicates, nums,
 return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list,
                   List<Integer> tempList, int [] nums, int start){

        list.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++){
            // skip duplicates
            if(i > start && nums[i] == nums[i-1]) continue;

            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

//------------------------------------------------------------------------------

    // Very simple and fast java solution

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        helper(res, each, 0, nums);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> each,
                       int pos, int[] n) {
        if (pos <= n.length) {
            res.add(each);
        }
        int i = pos;
        while (i < n.length) {
            each.add(n[i]);
            helper(res, new ArrayList<>(each), i + 1, n);
            each.remove(each.size() - 1);
            i++;
            while (i < n.length && n[i] == n[i - 1]) {i++;}
        }
        return;
    }
    /*
    The Basic idea is: use "while (i < n.length && n[i] == n[i - 1]) {i++;}" to
     avoid the duplicate.
     For example, the input is 2 2 2 3 4. Consider the helper function.
     The process is:

each.add(n[i]); --> add first 2 (index 0)
helper(res, new ArrayList<>(each), i + 1, n); --> go to recursion part,
list each is <2 (index 0)>
while (i < n.length && n[i] == n[i - 1]) {i++;} --> after this, i == 3,
add the element as in subset I
     */

//------------------------------------------------------------------------------

    //Standard DFS Java Solution
    public List<List<Integer>> subsetsWithDup3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        dfs(nums,0,new ArrayList<Integer>(),result);
        return result;
    }

    public void dfs(int[] nums, int index, List<Integer> path,
                    List<List<Integer>> result){
        result.add(path);
        for(int i=index;i<nums.length;i++){
            if(i>index&&nums[i]==nums[i-1]) continue;

            List<Integer> nPath= new ArrayList<>(path);
            nPath.add(nums[i]);
            dfs(nums,i+1,nPath,result);
        }
    }

//------------------------------------------------------------------------------
    //Share my 2ms java iteration solution (very simple and short)

    public List<List<Integer>> subsetsWithDup4(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        int begin = 0;

        for(int i = 0; i < nums.length; i++){

            if(i == 0 || nums[i] != nums[i - 1]) begin = 0;

            int size = result.size();

            for(int j = begin; j < size; j++){
                List<Integer> cur = new ArrayList<Integer>(result.get(j));
                cur.add(nums[i]);
                result.add(cur);
            }
            begin = size;
        }
        return result;
    }


//------------------------------------------------------------------------------
    //Accepted java iterative solution
    public List<List<Integer>> subsetsWithDup5(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int len = num.length;
        if (len == 0) return ans;
        // first, need to add the subset of num[0]
        ans.add(new ArrayList<Integer>());
        ans.add(new ArrayList<Integer>());
        ans.get(1).add(num[0]);

        // this is the number of lists that the previous number was added in.
        int nprev = 1;
        // if the current number is same as the prev one, it'll be only added in the
        // lists that has the prev number.

        for (int i = 1; i < len ; ++i){
            int size = ans.size();
            // if different
            if (num[i]!=num[i-1])
                // this means add num[i] to all lists in ans;
                nprev = size;
            for (int j = size-nprev; j < size; ++j){
                List<Integer> l = new ArrayList<Integer>(ans.get(j));
                l.add(num[i]);
                ans.add(l);
            }
        }
        return ans;
    }




//------------------------------------------------------------------------------
// Subsets vs. Subsets II: Add only 3 more lines to Subsets solution

    public class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());

            for (int num: nums) {

                List<List<Integer>> resDup = new ArrayList<>(res);

                for (List<Integer> list:resDup) {

                    List<Integer> tmpList = new ArrayList<>(list);
                    list.add(num);
                    res.add(tmpList);
                }
            }
            return res;
        }
    }
//    In this problem, we need to change two things:
//
//    Sort the input nums, so that we won't get lists such as [1,4]
// and [4, 1] at the same time.
//    Check duplicates when adding new list to res.
//    Here is Subset II solution based on subset I solution:
    public class Solution2 {

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            Arrays.sort(nums); //important: sort nums

            for (int num: nums) {
                List<List<Integer>> resDup = new ArrayList<>(res);

                for (List<Integer> list: resDup) {

                    List<Integer> tmp = new ArrayList<>(list);
                    tmp.add(num);

                    if (!res.contains(tmp))  //check duplicates
                        res.add(tmp);
                }
            }
            return res;
        }
    }




//-----------------------------------------------------------------------------/
    //My solution using bit masks
    public List<List<Integer>> subsetsWithDup6(int[] num) {
        //Sort the input
        Arrays.sort(num);
        int numberSets = 1 << num.length;
        List<List<Integer>> solution = new LinkedList<>();

        for(int i = 0; i<numberSets; i++){

            List<Integer> subset = new LinkedList<Integer>();

            for(int j = 0; j< num.length; j++){
                if((i & (1 << j)) > 0){
                    subset.add(num[j]);
                }
            }

            if(!solution.contains(subset))
                solution.add(subset);
        }

        return solution;
    }



//------------------------------------------------------------------------------
//Java solution using bit manipulation

    public List<List<Integer>> subsetsWithDup7(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1<<num.length;

        for(int i=0;i<subsetNum;i++){
            List<Integer> list = new ArrayList<>();
            boolean illegal=false;
            for(int j=0;j<num.length;j++){
                if((i>>j&1)==1){
                    if(j>0&&num[j]==num[j-1]&&(i>>(j-1)&1)==0){
                        illegal=true;
                        break;
                    }else{
                        list.add(num[j]);
                    }
                }
            }
            if(!illegal){
                lists.add(list);
            }

        }
        return lists;
    }

//------------------------------------------------------------------------------
    // Share simple recursive java solution
    public List<List<Integer>> subsetsWithDup8(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        LinkedList<Integer> temp = new LinkedList<Integer>();

        Rec(num,result,temp,0);

        return result;
    }
    private static void Rec(int[] a,List<List<Integer>> result,
                            LinkedList<Integer> temp, int current){
        result.add(new LinkedList(temp));

        for(int i=current;i<a.length;i++){

            if(i==current || a[i]!=a[i-1]){

                temp.add(a[i]);
                Rec(a,result,temp,i+1);
                temp.remove(temp.size()-1);
            }
        }
        return;
    }






}
