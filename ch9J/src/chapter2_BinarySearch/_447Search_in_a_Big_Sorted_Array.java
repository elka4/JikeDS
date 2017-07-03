package ch9J.chapter2_BinarySearch;

// Search in a Big Sorted Array

public class _447Search_in_a_Big_Sorted_Array {
    class ArrayReader {
        // get the number at index, return -1 if index is less than zero.
        public int get(int index){
            return index;
        }
    }
    /**
     * @param reader: An instance of ArrayReader can read number by index.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // Algorithm:
        // 1. get the index that ArrayReader.get(index) >= target in
        //    O(logk)
        // 2. Binary search the target between 0 and index
        
        int index = 1;
        while (reader.get(index - 1) < target) {
            index = index * 2;
        }
        
        int start = 0, end = index - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (reader.get(start) == target) {
            return start;
        }
        
        if (reader.get(end) == target) {
            return end;
        }
        
        return -1;
    }
}

/*
Given a big sorted array with positive integers sorted 
by ascending order. The array is so big so that you can 
not get the length of the whole array directly, and you 
can only access the kth number by ArrayReader.get(k) 
(or ArrayReader->get(k) for C++). Find the first index 
of a target number. Your algorithm should be in O(log k), 
where k is the first index of the target number.

Return -1, if the number doesn't exist in the array.

 Notice

If you accessed an inaccessible index (outside of the array), 
ArrayReader.get will return 2,147,483,647.

Have you met this question in a real interview? Yes
Example
Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.

Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.

Challenge 
O(log k), k is the first index of the given target number.

Tags 
Binary Search Sorted Array
Related Problems 
Easy First Position of Target 32 %
 * */

