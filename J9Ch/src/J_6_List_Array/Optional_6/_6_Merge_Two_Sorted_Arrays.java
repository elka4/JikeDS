package J_6_List_Array.Optional_6;

/** 6 Merge Two Sorted Arrays
 * Created by tianhuizhu on 6/28/17.
 */
public class _6_Merge_Two_Sorted_Arrays {

    class Solution {
        /**
         * @param A and B: sorted integer array A and B.
         * @return: A new sorted integer array
         */
        public int[] mergeSortedArray(int[] A, int[] B) {
            if (A == null || B == null) {
                return null;
            }

            int[] result = new int[A.length + B.length];
            int i = 0, j = 0, index = 0;

            while (i < A.length && j < B.length) {
                if (A[i] < B[j]) {
                    result[index++] = A[i++];
                } else {
                    result[index++] = B[j++];
                }
            }

            while (i < A.length) {
                result[index++] = A[i++];
            }
            while (j < B.length) {
                result[index++] = B[j++];
            }

            return result;
        }
    }
}
