package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _078_BackTracking_Subsets_M {
//    Subsets : https://leetcode.com/problems/subsets/

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


//    Simple Java Solution with For-Each loops
//    No messy indexing. Avoid the ConcurrentModificationException by using a temp list.

    public class Solution {
        public List<List<Integer>> subsets(int[] S) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());

            Arrays.sort(S);
            for(int i : S) {
                List<List<Integer>> tmp = new ArrayList<>();
                for(List<Integer> sub : res) {
                    List<Integer> a = new ArrayList<>(sub);
                    a.add(i);
                    tmp.add(a);
                }
                res.addAll(tmp);
            }
            return res;
        }
    }
//-------------------------------------------------------------------------


//-------------------------------------------------------------------------
// 递归：实现方式，一种实现DFS算法的一种方式
class Jiuzhang1 {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null) {
            return results;
        }

        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

        Arrays.sort(nums);
        helper(new ArrayList<Integer>(), nums, 0, results);
        return results;
    }


    // 递归三要素
    // 1. 递归的定义：在 Nums 中找到所有以 subset 开头的的集合，并放到 results
    private void helper(ArrayList<Integer> subset,
                        int[] nums,
                        int startIndex,
                        List<List<Integer>> results) {
        // 2. 递归的拆解
        // deep copy
        // results.add(subset);
        results.add(new ArrayList<Integer>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            // [1] -> [1,2]
            subset.add(nums[i]);
            // 寻找所有以 [1,2] 开头的集合，并扔到 results
            helper(subset, nums, i + 1, results);
            // [1,2] -> [1]  回溯
            subset.remove(subset.size() - 1);
        }

        // 3. 递归的出口
        // return;
    }
}


    // Non Recursion
    class Jiuzhang2 {
        /**
         * @param nums: A set of numbers.
         * @return: A list of lists. All valid subsets.
         */
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            int n = nums.length;
            Arrays.sort(nums);

            // 1 << n is 2^n
            // each subset equals to an binary integer between 0 .. 2^n - 1
            // 0 -> 000 -> []
            // 1 -> 001 -> [1]
            // 2 -> 010 -> [2]
            // ..
            // 7 -> 111 -> [1,2,3]
            for (int i = 0; i < (1 << n); i++) {
                List<Integer> subset = new ArrayList<Integer>();
                for (int j = 0; j < n; j++) {
                    // check whether the jth digit in i's binary representation is 1
                    if ((i & (1 << j)) != 0) {
                        subset.add(nums[j]);
                    }
                }
                result.add(subset);
            }
            return result;
        }
    }

//-------------------------------------------------------------------------



}
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */