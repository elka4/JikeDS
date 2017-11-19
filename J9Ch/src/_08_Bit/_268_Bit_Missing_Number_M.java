package _08_Bit;
import java.util.*;
import org.junit.Test;

//  268. Missing Number

//  https://leetcode.com/problems/missing-number/description/
//
public class _268_Bit_Missing_Number_M {

    //  4 Line Simple Java Bit Manipulate Solution with Explaination

/*    The basic idea is to use XOR operation. We all know that a^b^b =a, which means two xor operations with the same number will eliminate the number and reveal the original number.
    In this solution, I apply XOR operation to both the index and value of the array. In a complete array with no missing numbers, the index and value should be perfectly corresponding( nums[index] = index), so in a missing array, what left finally is the missing number.*/

    public int missingNumber1(int[] nums) {

        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }

/*    Hey guys, since the n numbers are from [0, n], we can just add all the numbers from [0, n] together and minus the sum of the n-1 numbers in array.

    Just like this.

    Java*/

    public static int missingNumber2(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++)
            sum += i - nums[i];
        return sum;
    }

//    Sum of n number: n(n+1)/2.

    public class Solution3 {
        public int missingNumber(int[] nums) {
            int sum=0;
            int len=nums.length+1;
            for(int n:nums)
                sum+=n;
            return ((len*(len-1)) /2-sum);

        }

    }
//-------------------------------------------------------------------------///////////
//3 different ideas: XOR, SUM, Binary Search. Java code

//1.XOR
    public int missingNumber4(int[] nums) { //xor
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
//2.SUM
    public int missingNumber5(int[] nums) { //sum
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }
//3.Binary Search
    public int missingNumber6(int[] nums) { //binary search
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
//    If the array is in order, I prefer Binary Search method. Otherwise, the XOR method is better.

//-------------------------------------------------------------------------///////////
//We can do SUM this way to avoid overflow.

    public class Solution7 {
        public int missingNumber(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += i;
                sum -= nums[i];
            }
            sum += nums.length;
            return sum;
        }
    }

//    You could also avoid sum overflow by using longs - something along the lines of this:

    public int missingNumber8(int[] nums)
    {
        long sum = nums[0];

        for(int i=1; i<nums.length; i++)
            sum = sum+nums[i];

        return (int) (nums.length*(nums.length+1)/2 - sum);
    }
//-------------------------------------------------------------------------///////////


//-------------------------------------------------------------------------///////////
}
/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

Credits:
 */

/*

 */