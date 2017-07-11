package J_6_Linked_List_Array.all;

/** 64 Merge Sorted Array
 * Created by tianhuizhu on 6/28/17.
 */
public class _64_Merge_Sorted_Array {

    class Solution {
        /**
         * @param A: sorted integer array A which has m elements,
         *           but size of A is m+n
         * @param B: sorted integer array B which has n elements
         * @return: void
         */
        public void mergeSortedArray(int[] A, int m, int[] B, int n) {
            int i = m-1, j = n-1, index = m + n - 1;
            while (i >= 0 && j >= 0) {
                if (A[i] > B[j]) {
                    A[index--] = A[i--];
                } else {
                    A[index--] = B[j--];
                }
            }
            while (i >= 0) {
                A[index--] = A[i--];
            }
            while (j >= 0) {
                A[index--] = B[j--];
            }
        }
    }
}
