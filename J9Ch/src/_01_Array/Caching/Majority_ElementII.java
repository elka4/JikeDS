package _01_Array.Caching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LeetCode – Majority Element II (Java)

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 */

public class Majority_ElementII {

    /*    Java Solution 1 - Using a Counter

            Time = O(n) and Space = O(n)
            最标准做法
            */

    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i: nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i)+1);
            }else{
                map.put(i, 1);
            }
        }

        List<Integer> result = new ArrayList<Integer>();

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() > nums.length/3){
                result.add(entry.getKey());
            }
        }

        return result;
    }

/*    Java Solution 2

    Time = O(n) and Space = O(1)

    Check out Majority Element I.*/

    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();

        Integer n1=null, n2=null;
        int c1=0, c2=0;

        for(int i: nums){
            if(n1!=null && i==n1.intValue()){
                c1++;
            }else if(n2!=null && i==n2.intValue()){
                c2++;
            }else if(c1==0){
                c1=1;
                n1=i;
            }else if(c2==0){
                c2=1;
                n2=i;
            }else{
                c1--;
                c2--;
            }
        }

        c1=c2=0;

        for(int i: nums){
            if(i==n1.intValue()){
                c1++;
            }else if(i==n2.intValue()){
                c2++;
            }
        }

        if(c1>nums.length/3)
            result.add(n1);
        if(c2>nums.length/3)
            result.add(n2);

        return result;
    }
    //A Linear Time Majority Vote Algorithm


//-------------------------------------------------------------------------------

    //JAVA-------------------Easy Version To Understand!!!!!!!!!!!!
    public List<Integer> majorityElement3(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        int number1 = nums[0], number2 = nums[0], count1 = 0,
                count2 = 0, len = nums.length;

        /*
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
         */
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }

        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        return result;
    }





//-------------------------------------------------------------------------------





//-------------------------------------------------------------------------------







//-------------------------------------------------------------------------------





//-------------------------------------------------------------------------------





//-------------------------------------------------------------------------------





}
