package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//  90. Subsets II

public class _090_BackTracking_Subsets_II_M {

//    Very simple and fast java solution
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        helper(res, each, 0, nums);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
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

    //Standard DFS Java Solution
    public class Solution2 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> result= new ArrayList<>();
            dfs(nums,0,new ArrayList<Integer>(),result);
            return result;
        }

        public void dfs(int[] nums,int index,List<Integer> path,List<List<Integer>> result){
            result.add(path);
            for(int i=index;i<nums.length;i++){
                if(i>index&&nums[i]==nums[i-1]) continue;
                List<Integer> nPath= new ArrayList<>(path);
                nPath.add(nums[i]);
                dfs(nums,i+1,nPath,result);
            }
        }
    }

    //Accepted java iterative solution
    class Solution3{
        public List<List<Integer>> subsetsWithDup(int[] num) {
            Arrays.sort(num);
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            int len = num.length;
            if (len == 0) return ans;

            ans.add(new ArrayList<Integer>()); // first, need to add the subset of num[0]
            ans.add(new ArrayList<Integer>());
            ans.get(1).add(num[0]);

            int nprev = 1; // this is the number of lists that the previous number was added in.
            // if the current number is same as the prev one, it'll be only added in the
            // lists that has the prev number.

            for (int i = 1; i < len ; ++i){
                int size = ans.size();
                if (num[i]!=num[i-1])   // if different
                    nprev = size;        // this means add num[i] to all lists in ans;
                for (int j = size-nprev; j < size; ++j){
                    List<Integer> l = new ArrayList<Integer>(ans.get(j));
                    l.add(num[i]);
                    ans.add(l);
                }
            }
            return ans;
        }
    }
//-------------------------------------------------------------------------////////////////

    // return List<List<Integer>>
    class Jiuzhang1 {
        /**
         * @param nums: A set of numbers.
         * @return: A list of lists. All valid subsets.
         */
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // write your code here
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            if (nums == null) return results;

            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }
            Arrays.sort(nums);

            List<Integer> subset = new ArrayList<Integer>();
            helper(nums, 0, subset, results);

            return results;


        }
        public void helper(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> results){
            results.add(new ArrayList<Integer>(subset));
            for(int i=startIndex; i<nums.length; i++){
                if (i != startIndex && nums[i]==nums[i-1]) {
                    continue;
                }
                subset.add(nums[i]);
                helper(nums, i+1, subset, results);
                subset.remove(subset.size()-1);
            }
        }
    }

    // return ArrayList<ArrayList<Integer>>
    class Jiuzhang2 {
        /**
         * @param nums: A set of numbers.
         * @return: A list of lists. All valid subsets.
         */
        public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
            // write your code here
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            if (nums == null) return results;

            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }
            Arrays.sort(nums);

            ArrayList<Integer> subset = new ArrayList<>();
            helper(nums, 0, subset, results);

            return results;


        }
        public void helper(int[] nums, int startIndex, ArrayList<Integer> subset, ArrayList<ArrayList<Integer>> results){
            results.add(new ArrayList<Integer>(subset));
            for(int i=startIndex; i<nums.length; i++){
                if (i != startIndex && nums[i]==nums[i-1]) {
                    continue;
                }
                subset.add(nums[i]);
                helper(nums, i+1, subset, results);
                subset.remove(subset.size()-1);
            }
        }
    }
}
/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

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