package J_1_strStr.all;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 17
 Subsets

 * Created by tianhuizhu on 6/28/17.
 */
public class _17_Subsets_my {

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> subset = new ArrayList<>();

        if (nums == null){
            return result;
        }
        if (nums.length == 0){
            result.add(new ArrayList<Integer>());
            return result;
        }
        Arrays.sort(nums);
        helper(nums, result, subset, 0);
        return result;
    }

    private void helper(int[] nums, ArrayList<ArrayList<Integer>> result,
                        ArrayList<Integer> subset, int position){

        result.add(new ArrayList<Integer>(subset));

        for(int i = position; i < nums.length; i++){
            subset.add(nums[i]);
            // !!! i + 1, NOT position + 1 !!!!
            helper(nums, result, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
    @Test
    public void test01(){
        int[] input = {1,2};
        System.out.println(subsets(input));
    }


// Non Recursion


}
