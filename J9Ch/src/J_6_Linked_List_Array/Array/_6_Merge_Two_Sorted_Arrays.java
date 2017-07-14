package J_6_Linked_List_Array.Array;

import org.junit.Test;

/** 6 Merge Two Sorted Arrays
 * Created by tianhuizhu on 6/28/17.
 */
public class _6_Merge_Two_Sorted_Arrays {

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

    @Test
    public void test01(){
        int[] A = {1,3,5};
        int[] B = {2,4,6};
        int[] result = mergeSortedArray(A,B);
        for (int i: result
             ) {
            System.out.println(i);
        }
    }

}
