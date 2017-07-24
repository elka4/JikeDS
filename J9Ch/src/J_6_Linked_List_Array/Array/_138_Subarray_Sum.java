package J_6_Linked_List_Array.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/** 138 Subarray Sum
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */

/*
Given an integer array, find a subarray where the sum of numbers is zero.
Your code should return the index of the first number and the index of the last number.
 */

// [-3, 1, 2, -3, 4], return [0, 2] or [1, 3]
public class _138_Subarray_Sum {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here

        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1);
                ans.add(i);
                return ans;
            }

            map.put(sum, i);
        }

        return ans;
    }

    @Test
    public void test01(){
        int[] nums = {-3, 1, 2, -3, 4};//[0, 2]
        ArrayList<Integer> result = subarraySum(nums);
        System.out.println(result);

    }

}
