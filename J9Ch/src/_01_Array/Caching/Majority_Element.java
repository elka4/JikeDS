package _01_Array.Caching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*
LeetCode – Majority Element (Java)

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times. (assume that the array is non-empty and the majority element always exist in the array.)
 */

public class Majority_Element {
/*    Java Solution 1 - Naive

    We can sort the array first, which takes time of nlog(n). Then scan once to find the longest consecutive substrings.*/

    public class Solution {
        public int majorityElement(int[] num) {
            if(num.length==1){
                return num[0];
            }

            Arrays.sort(num);

            int prev=num[0];
            int count=1;
            for(int i=1; i<num.length; i++){
                if(num[i] == prev){
                    count++;
                    if(count > num.length/2) return num[i];
                }else{
                    count=1;
                    prev = num[i];
                }
            }

            return 0;
        }
    }
/*    Java Solution 2 - Much Simpler

    Thanks to SK. His/her solution is much efficient and simpler.
    Since the majority always take more than a half space, the middle element is guaranteed to be the majority. Sorting array takes nlog(n). So the time complexity of this solution is nlog(n). Cheers!*/

    public int majorityElement2(int[] num) {
        if (num.length == 1) {
            return num[0];
        }

        Arrays.sort(num);
        return num[num.length / 2];
    }

    //Java Solution 3 - Linear Time Majority Vote Algorithm

    public int majorityElement3(int[] nums) {
        int result = 0, count = 0;

        for(int i = 0; i<nums.length; i++ ) {
            if(count == 0){
                result = nums[ i ];
                count = 1;
            }else if(result == nums[i]){
                count++;
            }else{
                count--;
            }
        }

        return result;
    }


//-------------------------------------------------------------------------//////
    //leet上全部的解法
    //Java solutions (sorting, hashmap, moore voting, bit manipulation).
    // Sorting
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // Hashtable
    public int majorityElement4(int[] nums) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
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

    // Moore voting algorithm
    public int majorityElement5(int[] nums) {
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

    // Bit manipulation
    public int majorityElement6(int[] nums) {
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



//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////







//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////






}
