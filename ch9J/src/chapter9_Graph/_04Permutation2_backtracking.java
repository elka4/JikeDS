package ch9J.chapter9_Graph;

//from leetcode discussion
//https://discuss.leetcode.com/topic/52105/2ms-beat-99-46-java-solution
// traditional backtracking way

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _04Permutation2_backtracking {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteUnique(result, nums, 0);
        return result;
    }

    private void permuteUnique(List<List<Integer>> result, int[] nums, int idx) {
        if (idx == nums.length) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int num : nums) {
                arrayList.add(num);
            }
            result.add(arrayList);
        }
        for (int i = idx; i < nums.length; i++) {
            if (!isDuplicate(nums, idx, i)) {
                swap(nums, idx, i);
                permuteUnique(result, nums, idx + 1);
                swap(nums, idx, i);
            }
        }
    }

    private boolean isDuplicate(int[] nums, int idx, int i) {
        for (int i1 = idx; i1 < i; i1++) {
            if (nums[i1] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test01 (){
        int[] input = {1, 2 , 3};
        System.out.println(permuteUnique(input));
    }
}
