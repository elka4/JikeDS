package _1_Array.TreeSet;

import java.util.HashMap;
import java.util.HashSet;
/*
LeetCode – Contains Duplicate II (Java)

Given an array of integers and an integer k, return true if and only if
there are two distinct indices i and j in the array such that
nums[i] = nums[j] and the difference between i and j is at most k.
 */

public class Contains_DuplicateII {


    //Java Solution 1
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int min = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                int preIndex = map.get(nums[i]);
                int gap = i-preIndex;
                min = Math.min(min, gap);
            }
            map.put(nums[i], i);
        }

        if(min <= k){
            return true;
        }else{
            return false;
        }
    }



    //Java Solution 2 - Simplified
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                int pre = map.get(nums[i]);
                if(i-pre<=k)
                    return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }


    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if(nums==null || nums.length<2 || k==0)
            return false;

        int i=0;

        HashSet<Integer> set = new HashSet<Integer>();

        for(int j=0; j<nums.length; j++){
            if(!set.add(nums[j])){
                return true;
            }

            if(set.size()>=k+1){
                set.remove(nums[i++]);
            }
        }

        return false;
    }


////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






}
