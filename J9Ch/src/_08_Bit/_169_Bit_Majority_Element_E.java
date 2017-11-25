package _08_Bit;
import java.util.*;
import org.junit.Test;

//  169. Majority Element
//  https://leetcode.com/problems/majority-element/description/
//  7:
//
public class _169_Bit_Majority_Element_E {
//------------------------------------------------------------------------------
    //1
//    O(n) time O(1) space fastest solution

    public class Solution1 {
        public int majorityElement(int[] num) {

            int major=num[0], count = 1;
            for(int i=1; i<num.length;i++){
                if(count==0){
                    count++;
                    major=num[i];
                }else if(major==num[i]){
                    count++;
                }else count--;

            }
            return major;
        }
    }
//------------------------------------------------------------------------------
    //2
//    got it,It's so good,my solution is like yours, I also used Arrays.sort(nums); but your solution is more Simple and efficient.Here is mine:

    public class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            int maj=0;
            for(int i=0;i<nums.length/2+1;i++){
                if(nums[i]==nums[i+nums.length/2]){
                    maj=nums[i];
                    break;
                }
            }
            return maj;
        }
    }

//------------------------------------------------------------------------------
    //3
//Java solutions (sorting, hashmap, moore voting, bit manipulation).

    // Sorting
    public int majorityElement4(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
//------------------------------------------------------------------------------
    //4
    // Hashtable
    public int majorityElement5(int[] nums) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        //Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
        int ret=0;
        for (int num: nums) {
            if (!myMap.containsKey(num))
                myMap.put(num, 1);
            else
                myMap.put(num, myMap.get(num)+1);
            if (myMap.get(num)>nums.length/2) {
                ret = num;
                break;
            }
        }
        return ret;
    }
//------------------------------------------------------------------------------
    //5
    // Moore voting algorithm
    public int majorityElement6(int[] nums) {
        int count=0, ret = 0;
        for (int num: nums) {
            if (count==0)
                ret = num;
            if (num!=ret)
                count--;
            else
                count++;
        }
        return ret;
    }
//------------------------------------------------------------------------------
    //6
    // Bit manipulation
    public int majorityElement7(int[] nums) {
        int[] bit = new int[32];
        for (int num: nums)
            for (int i=0; i<32; i++)
                if ((num>>(31-i) & 1) == 1)
                    bit[i]++;
        int ret=0;
        for (int i=0; i<32; i++) {
            bit[i]=bit[i]>nums.length/2?1:0;
            ret += bit[i]*(1<<(31-i));
        }
        return ret;
    }

//------------------------------------------------------------------------------
    //7
//  Share my solution [Java] - Count bits
/*Definitely not the fastest solution but I post it here for your reference since it's different from the rest I saw. The problem reminded me of the approach I followed at Single Number II (problem 137).

    We can iterate over the bits of all numbers and for every position find out if ones outnumber the zeros (among all numbers). If this is the case, the corresponding bit of the ret variable (which holds the result) is set. We essentially "construct" the number we look for.

    The following code is simple and should be easy to understand.*/

    public int majorityElement8(int[] num) {

        int ret = 0;

        for (int i = 0; i < 32; i++) {

            int ones = 0, zeros = 0;

            for (int j = 0; j < num.length; j++) {
                if ((num[j] & (1 << i)) != 0) {
                    ++ones;
                }
                else
                    ++zeros;
            }

            if (ones > zeros)
                ret |= (1 << i);
        }

        return ret;
    }

//------------------------------------------------------------------------------
}
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

/*

 */