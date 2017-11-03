package j_2_BinarySearch.Closest_Number;


// lintcode total occurrence of target
public class _17Total_Occurrence_of_Target {
	 /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int totalOccurrence(int[] A, int target) {
        // Write your code here
        int n = A.length;
        if (n == 0)
            return 0;
        if (A[n-1] < target || A[0] > target)
            return 0;
        
        int l = 0, r = n - 1;
        int start = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (A[mid] >= target) {
                start = mid;
                r = mid - 1;
            } else
                l = mid + 1;
        }
        if (A[start] != target)
            return 0;

        int end = n-1;
        l = 0; r = n-1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (A[mid] <= target) {
                end = mid;
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return end - start + 1;
    }


    public int totalOccurrence_mine(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        // Write your code here
        int start = 0;
        int end = A.length - 1;

        int first = 0;
        int last = A.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if  (A[mid] == target) {
                end = mid;                  //找start向前移动end
            } else if (target < A[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[start] == target) {           //找start先看start
            first = start;
        } else if (A[end] == target){
            first = end;
        } else {
            return 0;
        }

        start = 0;
        end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                start = mid;                //找end向后移动start
            } else if (target < A[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[end] == target) {             //找end先看end
            last = end;
        } else if (A[start] == target) {
            last = start;
        } else {
            return 0;
        }

        return last - first + 1;
    }
}


/*
Given a target number and an integer array sorted in ascending order. 
Find the total number of occurrences of target in the array.

Have you met this question in a real interview? Yes
Example
Given [1, 3, 3, 4, 5] and target = 3, return 2.

Given [2, 2, 3, 4, 6] and target = 4, return 1.

Given [1, 2, 3, 4, 5] and target = 6, return 0.

Challenge 
Time complexity in O(logn)

Tags 
Binary Search
Related Problems 
Medium Search for a Range 23 %
*/