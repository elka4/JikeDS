package J_1_strStr.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 17
 Subsets

 * Created by tianhuizhu on 6/28/17.
 */
public class _17_Subsets {

// 递归：实现方式，一种实现DFS算法的一种方式
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

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
                        ArrayList<ArrayList<Integer>> results) {
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
    @Test
    public void test01(){
        int[] input = {1,2};
        System.out.println(subsets(input));
    }

    @Test
    public void test(){
        System.out.println(subsets(new int[]{1,2, 2}));
    }

    /*
[[], [1], [1, 2], [1, 2, 2], [1, 2], [2], [2, 2], [2]]
    /*
    [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]

     
     */


//--------------------------------------------------------------------------------

// Non Recursion
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets2(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
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
            ArrayList<Integer> subset = new ArrayList<Integer>();
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


//------------------------------------------------------------------------------

    public List<List<Integer>> subsets3(int[] nums) {
        // Sort first, as required.
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        // Add an empty set first.
        lists.add(new ArrayList<Integer>());
        // For each possible length of subsets, we do DFS and Backtracking.
        for (int len = 1; len <= nums.length; len++) {
            dfs(new ArrayList<Integer>(), 0, len, nums, lists);
        }
        return lists;
    }

    private void dfs(List<Integer> cur, int start, int len, int[] nums, List<List<Integer>> lists) {
        if (cur.size() == len) {
            // If we've already got enough elements, we stop.
            lists.add(new ArrayList<Integer>(cur));
            return;
        }
        // Do backtracking from current start point.
        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs(cur, i + 1, len, nums, lists);
            cur.remove(cur.size() - 1);
        }
    }
}
