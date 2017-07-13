package _3Graph_PreClass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.PriorityQueue;

public class _1_BackTracking_PermutationsII {
	public List <List <Integer>> permuteUnique(int[] nums) {
        //Corn Cases Checked

		List <List <Integer>> res = new ArrayList<>();
        //Method: DFS, and using a HashSet <Integer> in each
        // pos to remove duplicates
        dfsHelper(res, nums, 0);
        return res;
    }

    private void dfsHelper(List <List <Integer>> res, int[] nums, int pos) {
        if (pos == nums.length) {
            List <Integer> list = new ArrayList <Integer>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        //用这个hashset来去重
        Set <Integer> used = new HashSet <Integer>();
        for (int i = pos; i  < nums.length; i++) {
            if (used.add(nums[i])) {

                swap(nums, i, pos);
                dfsHelper(res, nums, pos + 1);
                swap(nums, i, pos);

            }
        }
    }

    private void swap(int[] nums, int i, int pos) {
        int temp = nums[pos];
        nums[pos] = nums[i];
        nums[i] = temp;
    }

    @Test
    public void test01 (){
        int[] input = {1, 2 , 3};
        System.out.println(permuteUnique(input));
    }
}
