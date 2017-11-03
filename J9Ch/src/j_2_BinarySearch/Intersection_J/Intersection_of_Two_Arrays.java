package j_2_BinarySearch.Intersection_J;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//  http://lintcode.com/en/problem/intersection-of-two-arrays/
//  Intersection of Two Arrays

//  leetcode 349. Intersection of Two Arrays
//  https://leetcode.com/problems/intersection-of-two-arrays/description/
public class Intersection_of_Two_Arrays {
//    Three Java Solutions
//
//    Use two hash sets
//
//    Time complexity: O(n)

    public class Solution1 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> intersect = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                set.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (set.contains(nums2[i])) {
                    intersect.add(nums2[i]);
                }
            }
            int[] result = new int[intersect.size()];
            int i = 0;
            for (Integer num : intersect) {
                result[i++] = num;
            }
            return result;
        }
    }

//    Sort both arrays, use two pointers
//
//    Time complexity: O(nlogn)

    public class Solution2 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    set.add(nums1[i]);
                    i++;
                    j++;
                }
            }
            int[] result = new int[set.size()];
            int k = 0;
            for (Integer num : set) {
                result[k++] = num;
            }
            return result;
        }
    }

//    Binary search
//
//    Time complexity: O(nlogn)

    public class Solution3 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Arrays.sort(nums2);
            for (Integer num : nums1) {
                if (binarySearch(nums2, num)) {
                    set.add(num);
                }
            }
            int i = 0;
            int[] result = new int[set.size()];
            for (Integer num : set) {
                result[i++] = num;
            }
            return result;
        }

        public boolean binarySearch(int[] nums, int target) {
            int low = 0;
            int high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return false;
        }
    }


///////////////////////////////////////////////////////////////////////////////
    //    5ms Java Using 1 hashset and time complexity of O(m+n)

    class Solution4{
        public int[] intersection(int[] nums1, int[] nums2) {

            HashSet<Integer> set = new HashSet<Integer>();
            ArrayList<Integer> res = new ArrayList<Integer>();
            //Add all elements to set from array 1
            for(int i =0; i< nums1.length; i++) set.add(nums1[i]);
            for(int j = 0; j < nums2.length; j++) {
                // If present in array 2 then add to res and remove from set
                if(set.contains(nums2[j])) {
                    res.add(nums2[j]);
                    set.remove(nums2[j]);
                }
            }
            // Convert ArrayList to array
            int[] arr = new int[res.size()];
            for (int i= 0; i < res.size(); i++) arr[i] = res.get(i);
            return arr;
        }
    }


///////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    // version 1: sort & merge
    public class Solution11 {
        /**
         * @param nums1 an integer array
         * @param nums2 an integer array
         * @return an integer array
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            int i = 0, j = 0;
            int[] temp = new int[nums1.length];
            int index = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    if (index == 0 || temp[index - 1] != nums1[i]) {
                        temp[index++] = nums1[i];
                    }
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }

            int[] result = new int[index];
            for (int k = 0; k < index; k++) {
                result[k] = temp[k];
            }

            return result;
        }
    }

    // version 2: hash map
    public class Solution22 {
        /**
         * @param nums1 an integer array
         * @param nums2 an integer array
         * @return an integer array
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return null;
            }

            HashSet<Integer> hash = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                hash.add(nums1[i]);
            }

            HashSet<Integer> resultHash = new HashSet<>();
            for (int i = 0; i < nums2.length; i++) {
                if (hash.contains(nums2[i]) && !resultHash.contains(nums2[i])) {
                    resultHash.add(nums2[i]);
                }
            }

            int size = resultHash.size();
            int[] result = new int[size];
            int index = 0;
            for (Integer num : resultHash) {
                result[index++] = num;
            }

            return result;
        }
    }

    // version 3: sort & binary search
    public class Solution33 {
        /**
         * @param nums1 an integer array
         * @param nums2 an integer array
         * @return an integer array
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return null;
            }

            HashSet<Integer> set = new HashSet<>();

            Arrays.sort(nums1);
            for (int i = 0; i < nums2.length; i++) {
                if (set.contains(nums2[i])) {
                    continue;
                }
                if (binarySearch(nums1, nums2[i])) {
                    set.add(nums2[i]);
                }
            }

            int[] result = new int[set.size()];
            int index = 0;
            for (Integer num : set) {
                result[index++] = num;
            }

            return result;
        }

        private boolean binarySearch(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return false;
            }

            int start = 0, end = nums.length - 1;
            while (start + 1 < end) {
                int mid = (end - start) / 2 + start;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (nums[start] == target) {
                return true;
            }
            if (nums[end] == target) {
                return true;
            }

            return false;
        }
    }
}

/*
Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

 Notice: Each element in the result must be unique.

Example
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 */