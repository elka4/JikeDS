package _3Graph_PreClass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _1_Backtracking_Permutation_2 {
	public List <List <Integer>> permute1(int[] nums) {
        List <List <Integer>> res = new ArrayList <List <Integer>>();
        if(nums == null || nums.length == 0)
            return res;
        List <Integer> list = new ArrayList <Integer>();
        helper(nums, res, list, 0);
        return res;
    }

    private void helper(int[] nums, List <List <Integer>> res, List <Integer> list, int pos){
        //Base Case:
        if(pos == nums.length){
            res.add(new ArrayList <Integer>(list));
            return;
        }

        //Main Cases:
        for(int i = pos; i  < nums.length; i++){
            list.add(nums[i]);
            swap(nums, pos, i);
            print(nums);
        //helper(nums, res, list, level + 1);
            helper(nums, res, list, pos + 1);//这样对不对？
            //print(nums);
            swap(nums, pos, i);
            list.remove(list.size() - 1);
        }

        return;
    }

	private void swap(int[] nums, int pos, int i) {
        int temp = nums[pos];
        nums[pos] = nums[i];
        nums[i] = temp;
	}

    @Test
    public void test01 (){
        int[] input = {1, 2 , 3};
        System.out.println(permute1(input));
    }

    private void print(int[] nums){
        System.out.print("nums: ");
        for (int i:nums
             ) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
