package J_2_Binary_Search.Required_10;

/**62. Search in Rotated Sorted Array
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */
public class _62_Search_in_Rotated_Sorted_Array {

    public class Solution {
        public int search(int[] A, int target) {
            if (A == null || A.length == 0) {
                return -1;
            }

            int start = 0;
            int end = A.length - 1;
            int mid;

            while (start + 1 < end) {
                mid = start + (end - start) / 2;
                if (A[mid] == target) {
                    return mid;
                }
                if (A[start] < A[mid]) {
                    // situation 1, red line
                    if (A[start] <= target && target <= A[mid]) {
                        end = mid;
                    } else {
                        start = mid;
                    }
                } else {
                    // situation 2, green line
                    if (A[mid] <= target && target <= A[end]) {
                        start = mid;
                    } else {
                        end = mid;
                    }
                }
            } // while

            if (A[start] == target) {
                return start;
            }
            if (A[end] == target) {
                return end;
            }
            return -1;
        }
    }
    ////////////////////////////////////////////////////////
    public int search_MY (int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;

        while(start + 1 < end){
            int mid = start + (end - start) / 2;

            if (A[mid] > A[start]){
                if(A[start] <= target && target <= A[mid]){
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (A[mid] < A[start]){
                if(A[mid] <= target && target <= A[end]){
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target){
            return start;
        } else if (A[end] == target){
            return end;
        } else {
            return -1;
        }
    }
}
