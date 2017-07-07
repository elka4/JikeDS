package chapter7_Array;

import java.util.ArrayList;
import java.util.HashMap;

public class _14Subarray_Sum {
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