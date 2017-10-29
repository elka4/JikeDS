package _BinarySearch;
import java.util.*;
import org.junit.Test;
public class _035_BinarySearch_Search_Insert_Position_M {

///////////////////////////////////////////////////////////
    //jiuzhang
// version 1: find the first position >= target
public class Jiuzhang1 {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }
}

// version 2: find the last position < target, return +1， 要特判一下target小于所有数组里面的元素

    public class Jiuzhang2 {
        public int searchInsert(int[] A, int target) {
            if (A == null || A.length == 0) {
                return 0;
            }
            int start = 0;
            int end = A.length - 1;
            int mid;

            if (target < A[0]) {
                return 0;
            }
            // find the last number less than target
            while (start + 1 < end) {
                mid = start + (end - start) / 2;
                if (A[mid] == target) {
                    return mid;
                } else if (A[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (A[end] == target) {
                return end;
            }
            if (A[end] < target) {
                return end + 1;
            }
            if (A[start] == target) {
                return start;
            }
            return start + 1;
        }
    }
}
/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0


 */