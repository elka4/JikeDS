package _08_Bit;
import java.util.*;
import org.junit.Test;

//  169. Majority Element
//  https://leetcode.com/problems/majority-element/description/
//  6: 1，
//
public class _169_Bit_Majority_Element_E {
//------------------------------------------------------------------------------
    //https://leetcode.com/problems/majority-element/solution/
//------------------------------------------------------------------------------
    //1
    //这办法很好
    //O(n) time O(1) space fastest solution
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


    public int majorityElement1(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
    }
//------------------------------------------------------------------------------
    //2
    //got it,It's so good,my solution is like yours, I also used Arrays.sort(nums);
    // but your solution is more Simple and efficient.Here is mine:
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
    class Solution3{
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length/2];
        }
    }

//------------------------------------------------------------------------------
    //4
    // Hashtable
    class Solution4{
        public int majorityElement(int[] nums) {
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
    }


//------------------------------------------------------------------------------
    //5
    //这个不敢说复杂度一定是最低的，写起来有点小麻烦，但是一定要懂这种bit map的做法
    // Bit manipulation
    class Solution6{
        public int majorityElement(int[] nums) {
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
    }
/*
第七种是Bit manipulation，时间复杂度为O(n)，需要32个计数器，每个计数器记录所有数组某一位的 1 的数目，由于多数元素一定存在，那么 1 的数目和 0 的数目必然不同，多者即为多数元素那一位的取值
 */

//------------------------------------------------------------------------------
    //6
    //Share my solution [Java] - Count bits
/*Definitely not the fastest solution but I post it here for your reference since it's different from the rest I saw. The problem reminded me of the approach I followed at Single Number II (problem 137).

    We can iterate over the bits of all numbers and for every position find out if ones outnumber the zeros (among all numbers). If this is the case, the corresponding bit of the ret variable (which holds the result) is set. We essentially "construct" the number we look for.

    The following code is simple and should be easy to understand.*/

    class Solution7{
        public int majorityElement(int[] num) {
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
    }


//------------------------------------------------------------------------------
}
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

/*

 */