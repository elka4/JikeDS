package J_2_Binary_Search.all;

/** 75. Find Peak Element
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */
public class _75_Find_Peak_Element {

    class Solution {
        /**
         * @param A: An integers array.
         * @return: return any of peek positions.
         */
        public int findPeak(int[] A) {
            // write your code here
            int start = 1, end = A.length-2; // 1.答案在之间，2.不会出界
            while(start + 1 <  end) {
                int mid = (start + end) / 2;
                if(A[mid] < A[mid - 1]) {
                    end = mid;
                } else if(A[mid] < A[mid + 1]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            if(A[start] < A[end]) {
                return end;
            } else {
                return start;
            }
        }
    }

    public int findPeak_MY(int[] A) {
        // write your code here
        int start = 0;
        int end = A.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(A[mid] < A[mid + 1]){
                start = mid;
            } else{
                end = mid;
            }
        }
        return A[start] > A[end] ? start : end;
    }
}
