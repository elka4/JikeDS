package _BinarySearch.H_Index;

public class _275_BinarySearch_HIndex_II_M {
//O(logN)-time O(1)-space Easy Solution with Detailed Explanations (C++/Java/Python)
    public class Solution {
        public int hIndex(int[] citations) {
            int len = citations.length;

            int first = 0;
            int mid;
            int count = len;
            int step;

            while (count > 0) {
                step = count / 2;
                mid = first + step;
                if (citations[mid] < len - mid) {
                    first = mid + 1;
                    count -= (step + 1);
                }
                else {
                    count = step;
                }
            }

            return len - first;
        }
    }


/*    Java binary search, simple and clean
    The idea is to search for the first index from the sorted array so that :

    citations[index] >= length(citations) - index.

            And return (length - index) as the result.
    Here is the code:*/

    public int hIndex(int[] citations) {
        int len = citations.length;
        int lo = 0, hi = len - 1;
        while (lo <= hi) {
            int med = (hi + lo) / 2;
            if (citations[med] == len - med) {
                return len - med;
            } else if (citations[med] < len - med) {
                lo = med + 1;
            } else {
                //(citations[med] > len-med), med qualified as a hIndex,
                // but we have to continue to search for a higher one.
                hi = med - 1;
            }
        }
        return len - lo;
    }
}
/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?


 */