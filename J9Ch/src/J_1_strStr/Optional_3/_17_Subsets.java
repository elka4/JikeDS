package J_1_strStr.Optional_3;

import org.junit.Test;

import java.util.*;
import java.util.Arrays;
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


//-------------------------------------------------------------------------//////////

public List<List<Integer>> subsetsX(int[] nums) {
    // write your code here
    List<List<Integer>> result = new ArrayList<>();
    if(nums == null ){
        return result;
    }
    List<Integer> list = new ArrayList<>();
    if(nums.length == 0){
        result.add(list);
        return result;
    }

    Arrays.sort(nums);
    helperX(nums, result, list, 0);
    return result;
}

    private void helperX(int[] nums,  List<List<Integer>> result, List<Integer> list, int index){
        result.add(new ArrayList(list));

        for(int i = index; i < nums.length; i++){
            list.add(nums[i]);
            System.out.println("list.add(nums[i]); " + nums[i]);
            helperX(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void test0X(){
        int[] input = {1,2};
        System.out.println(subsetsX(input));
    }

//-------------------------------------------------------------------------//////////

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
}
