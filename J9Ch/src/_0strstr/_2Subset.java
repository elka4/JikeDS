package _1strstr;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by tzh on 1/14/17.
 */
public class _2Subset {

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        //int flag = 0;
        helper(nums, result, list, 0);
        return result;
    }

    private void helper(int[] nums, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int flag) {
        result.add(new ArrayList<Integer>(list));
        for (int i = flag; i < nums.length; i++) {
            list.add(nums[i]);
            helper(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }


    }

    @Test

    public void test01() {
        int[] nums = {0};
        ArrayList<ArrayList<Integer>> result = subsets(nums);
        System.out.print(result);
    }

    @Test
    public void test02() {
        int[] nums = {1, 2, 3, 4};
        ArrayList<ArrayList<Integer>> result = subsets(nums);
        System.out.print(result);
    }
}

