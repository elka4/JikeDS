package lintcode._1strstr;
import org.junit.Test;

import java.util.*;

/**
 * Created by tzh on 1/14/17.
 */
public class _6Permutation {

    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        ArrayList<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (nums == null ) {
            //result.add(list);
            return result;
        }
        if (nums.length == 0) {
            result.add(list);
            return result;
        }
        helper(nums, result, list);

        return result;
    }

    private void helper(int[] nums, ArrayList<List<Integer>> result, ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            result.add(new ArrayList(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            helper(nums, result, list);
            list.remove(list.size() - 1);
        }
    }


    @Test
    public void test01() {
        int[] nums = {0};
        List<List<Integer>> result = permute(nums);
        System.out.print(result);
    }

    @Test
    public void test02() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.print(result);
    }
}
