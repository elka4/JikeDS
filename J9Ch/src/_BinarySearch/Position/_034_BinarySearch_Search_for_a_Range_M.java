package _BinarySearch.Position;


//
//
//
public class _034_BinarySearch_Search_for_a_Range_M {

//------------------------------------------------------------------------------
    // 9Ch
    public class Solution {
        public int[] searchRange(int[] A, int target) {
            if (A.length == 0) {
                return new int[]{-1, -1};
            }

            int start, end, mid;
            int[] bound = new int[2];

            // search for left bound
            start = 0;
            end = A.length - 1;
            while (start + 1 < end) {
                mid = start + (end - start) / 2;
                if (A[mid] == target) {
                    end = mid;
                } else if (A[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (A[start] == target) {
                bound[0] = start;
            } else if (A[end] == target) {
                bound[0] = end;
            } else {
                bound[0] = bound[1] = -1;
                return bound;
            }

            // search for right bound
            start = 0;
            end = A.length - 1;
            while (start + 1 < end) {
                mid = start + (end - start) / 2;
                if (A[mid] == target) {
                    start = mid;
                } else if (A[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if (A[end] == target) {
                bound[1] = end;
            } else if (A[start] == target) {
                bound[1] = start;
            } else {
                bound[0] = bound[1] = -1;
                return bound;
            }

            return bound;
        }
    }
//------------------------------------------------------------------------------

}
/*
Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].


 */

/*
给定一个包含 n 个整数的排序数组，找出给定目标值 target 的起始和结束位置。

如果目标值不在数组中，则返回[-1, -1]

您在真实的面试中是否遇到过这个题？ Yes
样例
给出[5, 7, 7, 8, 8, 10]和目标值target=8,

返回[3, 4]
 */