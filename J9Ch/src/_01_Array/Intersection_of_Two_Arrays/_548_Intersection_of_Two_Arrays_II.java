package _01_Array.Intersection_of_Two_Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 548 Intersection of Two Arrays II
 * Created by tianhuizhu on 6/28/17.
 */

// Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].



public class _548_Intersection_of_Two_Arrays_II {
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums1.length; ++i) {
            if (map.containsKey(nums1[i]))
                map.put(nums1[i], map.get(nums1[i]) + 1);
            else
                map.put(nums1[i], 1);
        }

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 0; i < nums2.length; ++i)
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                results.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }

        int result[] = new int[results.size()];
        for(int i = 0; i < results.size(); ++i)
            result[i] = results.get(i);

        return result;
    }

//------------------------------------------------------------------------------


}
/*
Given two arrays, write a function to compute their intersection.

 Notice

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Have you met this question in a real interview? Yes
Example
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 */
