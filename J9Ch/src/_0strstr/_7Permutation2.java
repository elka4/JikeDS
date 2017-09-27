package _1strstr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by tzh on 1/14/17.
 */
public class _7Permutation2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        ArrayList<List<Integer>> result = new ArrayList<>();

        ArrayList<Integer> list = new ArrayList<>();

        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(list);
            return result;
        }
        Arrays.sort(nums);
        int[] visited = new int[nums.length];
        helper(nums, result, list, visited);
        return result;

    }
    private void helper(int[] nums, ArrayList<List<Integer>> result,
                        ArrayList<Integer> list, int[] visited) {

        if (list.size() == nums.length) {
            result.add(new ArrayList(list));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if (visited[i] == 1 ||
                    (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)
                    ){
                continue;
            }
            visited[i] = 1;
            list.add(nums[i]);
            helper(nums, result, list, visited);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }

    @Test
    public void test01(){
        int[] nums = {1, 2, 2};
        List<List<Integer>> result =  permuteUnique(nums);
        System.out.print(result);

    }


}

