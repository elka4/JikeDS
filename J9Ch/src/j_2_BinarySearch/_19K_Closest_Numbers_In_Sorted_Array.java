package j_2_BinarySearch;

//k Closest Number in Sorted Array

public class _19K_Closest_Numbers_In_Sorted_Array {
	 /**
     * @param A an integer array
     * @param target an integer
     * @param k a non-negative integer
     * @return an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        int[] result = new int[k];
        
        if (A == null || A.length == 0) {
            return A;
        }
        if (k > A.length) {
            return A;
        }
        
        int index = firstIndex(A, target);
        
        int start = index - 1;
        int end = index;
        for (int i = 0; i < k; i++) {
            if (start < 0) {
                result[i] = A[end++];
            } else if (end >= A.length) {
                result[i] = A[start--];
            } else {
                if (target - A[start] <= A[end] - target) {
                    result[i] = A[start--];
                } else {
                    result[i] = A[end++];
                }
            }
        }
        return result;
    }
    
    private int firstIndex(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target) {
                start = mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[start] >= target) {
            return start;
        }
        if (A[end] >= target) {
            return end;
        }
        return A.length;
    }
}


/*
 *Given a target number, a non-negative integer k and an integer array 
 *A sorted in ascending order, find the k closest numbers to target in A, 
 *sorted in ascending order by the difference between the number and target. 
 *Otherwise, sorted in ascending order by number if the difference is same.

Have you met this question in a real interview? Yes
Example
Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].

Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].

Challenge 
O(logn + k) time complexity.

Tags 
Binary Search Two Pointers
Related Problems 
Easy Closest Number in Sorted Array 38 %
 * */
