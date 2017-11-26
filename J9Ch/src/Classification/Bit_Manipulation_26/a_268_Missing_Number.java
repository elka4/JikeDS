package Classification.Bit_Manipulation_26;
import java.util.*;

//268. Missing Number

public class a_268_Missing_Number {
//-------------------------------------------------------------------------------

    //4 Line Simple Java Bit Manipulate Solution with Explaination
    //    The basic idea is to use XOR operation. We all know that a^b^b =a,
    // which means two xor operations with the same number will eliminate the number
    // and reveal the original number.
    //    In this solution, I apply XOR operation to both the index and value of the array.
    // In a complete array with no missing numbers, the index and value should be perfectly
    // corresponding( nums[index] = index), so in a missing array,
    // what left finally is the missing number.

    //a^b^b =a
    public int missingNumber(int[] nums) {

        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            //i == nums[i] in complete array
            xor = xor ^ i ^ nums[i];
        }
        //因为对于missing来说，xor做了一个 ^i操作，需要再做一次来得到原始的xor（a^b^b =a）
        return xor ^ i;
    }

//-------------------------------------------------------------------------------
    //3 different ideas: XOR, SUM, Binary Search. Java code
    //1.XOR
    //这个最简单易懂
    public int missingNumber2(int[] nums) { //xor
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

//-------------------------------------------------------------------------------

    //2.SUM
    public int missingNumber3(int[] nums) { //sum
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }
//-------------------------------------------------------------------------------

    //3.Binary Search
    //二分法也不错，要研究一下
    public int missingNumber4(int[] nums) { //binary search
        Arrays.sort(nums);
        int left = 0, right = nums.length, mid= (left + right)/2;
        while(left<right){
            mid = (left + right)/2;
            if(nums[mid]>mid) right = mid;
            else left = mid+1;
        }
        return left;
    }
//    Summary:
//    If the array is in order, I prefer Binary Search method. Otherwise,
// the XOR method is better.

//-------------------------------------------------------------------------------

//    Java solution O(1) space and O(n) in time
//    Pretty simple since we are told that we are missing only one number in [1,n],
// we just need to look at the difference between the sum([1,n]) = n * (n+1) / 2
//

//高斯的数学方法，求实际和 和 理论和。
    public class Solution {
        public int missingNumber(int[] nums) {
            int sum = 0;
            for(int num: nums)
                sum += num;

            return (nums.length * (nums.length + 1) )/ 2 - sum;
        }
    }
//    With a slight mod to the return statement the situation for large n is taken care of.
// The needed modification is
//
//return ( (nums.length * (nums.length + 1) ) - 2 * sum ) / 2;

//-------------------------------------------------------------------------------
}
/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?


 */