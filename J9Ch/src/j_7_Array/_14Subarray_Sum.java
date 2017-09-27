package j_7_Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class _14Subarray_Sum {
     /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */


     //次题本质还是prearray sum
     // 如果一个subarray sum （i + 1, j） == 0,
     //presum (0~i) == presum (0~j)
     //hashmap 用来存储presum（0～index）和index。
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here

        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            System.out.println("sum " + sum);
            if (map.containsKey(sum)) {
                System.out.println(map);
                ans.add(map.get(sum) + 1);
                ans.add(i);
                return ans;
            }

            map.put(sum, i);
            System.out.println("map " + map);

        }

        return ans;
    }

    @Test
    public void test(){
        int[] nums = {-3, 1, 2, -3, 4};
        System.out.println(subarraySum(nums));
    }

    @Test
    public void test02(){
        int[] nums = {-3, 2, 4, -6, -2};
        System.out.println(subarraySum(nums));
    }
}

/*Given an integer array, find a subarray where the sum of numbers
 *  is zero. Your code should return the index of the first number 
 *  and the index of the last number.
 

 Notice

There is at least one subarray that it's sum equals to zero.

Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

Tags 
Subarray Hash Table
Related Problems 
Medium Submatrix Sum 22 %
Medium Minimum Size Subarray Sum 25 %
Medium Subarray Sum Closest 17 %*/