package ch9J.chapter1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by tianhuizhu on 6/18/17.
 */
public class _17Subsets_test {
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        ArrayList<Integer> subset = new ArrayList<Integer>();
        helper(result, nums, subset, 0);
        return result;

    }

    private void helper (ArrayList<ArrayList<Integer>> result, int[] nums, ArrayList<Integer> subset, int position){
        result.add(new ArrayList<Integer>(subset));

        for (int i = position; i < nums.length; i++){
            subset.add(nums[i]);
            helper(result, nums, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
    @Test
    public void test01(){
        _17Subsets_test sub = new _17Subsets_test();
        int[] input = {};
        ArrayList<ArrayList<Integer>> result = sub.subsets(input);
        System.out.println(result);

    }

}
