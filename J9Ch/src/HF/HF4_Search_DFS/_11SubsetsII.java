package HF.HF4_Search_DFS;

import org.junit.Test;

import java.util.*;

//Subsets II
public class _11SubsetsII {
    // return List<List<Integer>>
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

//////////////////////////////////////////////////////////////////////////////

    // return ArrayList<ArrayList<Integer>>
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] nums) {
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

    @Test
    public void test01(){
        System.out.println(subsetsWithDup2(new int[]{1,2,2}));
    }
//////////////////////////////////////////////////////////////////////////////
}
/*
给定一个可能具有重复数字的列表，返回其所有可能的子集

 注意事项

子集中的每个元素都是非降序的
两个子集间的顺序是无关紧要的
解集中不能包含重复子集
您在真实的面试中是否遇到过这个题？ Yes
样例
如果 S = [1,2,2]，一个可能的答案为：

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

 */
