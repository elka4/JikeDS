package _1_Array.Two_Pointers_3;
import java.util.*;


/**
 * Created by tianhuizhu on 6/21/17.
 */

/*

Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?

For example, given sorted array A = [1,1,1,2,2,3], your function should return length = 5, and A is now [1,1,2,2,3].
 */

public class b_80_Remove_Duplicates_from_Sorted_Array_II {


    //Share my O(N) time and O(1) solution when duplicates are allowed at most K times
    int removeDuplicates(int A[], int n, int k) {

        if (n <= k) return n;

        int i = 1, j = 1;
        int cnt = 1;
        while (j < n) {
            if (A[j] != A[j-1]) {
                cnt = 1;
                A[i++] = A[j];
            }
            else {
                if (cnt < k) {
                    A[i++] = A[j];
                    cnt++;
                }
            }
            ++j;
        }
        return i;
    }

/////////////////////////////////////?
    //Java Solution 1

    //We can not change the given array's size, so we only change the first k elements of the array which has duplicates removed.

    public int removeDuplicates2(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        int pre = A[0];
        boolean flag = false;
        int count = 0;

        // index for updating
        int o = 1;

        for (int i = 1; i < A.length; i++) {
            int curr = A[i];

            if (curr == pre) {
                if (!flag) {
                    flag = true;
                    A[o++] = curr;

                    continue;
                } else {
                    count++;
                }
            } else {
                pre = curr;
                A[o++] = curr;
                flag = false;
            }
        }

        return A.length - count;
    }




/////////////////////////////////////?

    //Java Solution 2

    public int removeDuplicates3(int[] A) {
        if (A.length <= 2)
            return A.length;

        int prev = 1; // point to previous
        int curr = 2; // point to current

        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }

        return prev + 1;
    }



/////////////////////////////////////?



}
