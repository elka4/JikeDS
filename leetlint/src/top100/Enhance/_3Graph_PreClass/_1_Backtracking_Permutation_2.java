package top100.Enhance._3Graph_PreClass;

import java.util.*;

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
//            helper(nums, res, list, level + 1);
            helper(nums, res, list, pos + 1);//这样对不对？
            swap(nums, pos, i);
            list.remove(list.size() - 1);
        }

        return;
}

	private void swap(int[] nums, int pos, int i) {
		// TODO Auto-generated method stub
		
	}
}
