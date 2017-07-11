package J_2_Binary_Search.Required_10;
import org.junit.Test;

/**459. Closest Number in Sorted Array
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _459_Closest_Number_in_Sorted_Array_my {

    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int index = firstIndex(A, target);
        if (index == 0) {
            return 0;
        }
        if (index == A.length) {
            return A.length - 1;
        }

        if (target - A[index - 1] < A[index] - target) {
            return index - 1;
        }
        return index;
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

    ////////////////////
    public int closestNumber2(int[] A, int target) {
        // Write your code here
        if (A == null) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.abs(A[start] - target) <= Math.abs(A[end] - target) ? start : end;

    }

    @Test
    public void test01(){
        int[] A = {1,4,6,10,20};
        int B = 21;
        System.out.println(closestNumber(A,B));
    }

    @Test
    public void test02(){
        int[] A = {1,4,6,10,20};
        int B = 21;
        System.out.println(closestNumber2(A,B));
    }
    @Test
    public void test03(){
        int[] A = {1,2,3};
        int B = 2;
        System.out.println(closestNumber2(A,B));
    }
}

/*
Given [1, 2, 3] and target = 2, return 1.

        Given [1, 4, 6] and target = 3, return 1.

        Given [1, 4, 6] and target = 5, return 1 or 2.

        Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.*/
