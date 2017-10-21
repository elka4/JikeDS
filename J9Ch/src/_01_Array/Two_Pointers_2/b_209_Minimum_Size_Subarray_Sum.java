package _01_Array.Two_Pointers_2;

import org.junit.Test;

/**
 * Created by tianhuizhu on 6/21/17.
 */
/*
LeetCode – Minimum Size Subarray Sum (Java)

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.

Analysis

We can use 2 points to mark the left and right boundaries of the sliding window. When the sum is greater than the target, shift the left pointer; when the sum is less than the target, shift the right pointer.
 */


public class b_209_Minimum_Size_Subarray_Sum {

/*
Java Solution - two pointers

A simple sliding window solution.
 */

    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null || nums.length==1)
            return 0;

        int result = nums.length;

        int start=0;
        int sum=0;
        int i=0;
        boolean exists = false;

        while(i<=nums.length){
            if(sum>=s){
                exists=true; //mark if there exists such a subarray
                if(start==i-1){
                    return 1;
                }

                result = Math.min(result, i-start);
                sum=sum-nums[start];
                start++;

            }else{
                if(i==nums.length)
                    break;
                sum = sum+nums[i];
                i++;
            }
        }

        if(exists)
            return result;
        else
            return 0;
    }


    @Test
    public void test01(){
        int s = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }
//////////////////////////////////////?

    //Similarly, we can also write it in a more readable way.

    public int minSubArrayLen2(int s, int[] nums) {
        if(nums==null||nums.length==0)
            return 0;

        int i=0;
        int j=0;
        int sum=0;

        int minLen = Integer.MAX_VALUE;

        while(j<nums.length){
            if(sum<s){
                sum += nums[j];
                j++;
            }else{
                minLen = Math.min(minLen, j-i);
                if(i==j-1)
                    return 1;

                sum -=nums[i];
                i++;
            }
        }

        while(sum>=s){
            minLen = Math.min(minLen, j-i);

            sum -=nums[i++];
        }

        return minLen==Integer.MAX_VALUE? 0: minLen;
    }

    @Test
    public void test02(){
        int s = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen2(7, nums));
    }
/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////

}
