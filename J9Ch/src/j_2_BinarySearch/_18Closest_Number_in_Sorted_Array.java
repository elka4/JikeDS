package j_2_BinarySearch; import org.junit.Test;

//  Closest Number in Sorted Array

public class _18Closest_Number_in_Sorted_Array {
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
        System.out.println("firstIndex: " + index);

        // target比最小的还小
        if (index == 0) {
            System.out.println("index == 0");
            return 0;
        }

        // target比最大的还大
        if (index == A.length) {
            System.out.println("index == A.length");
            return A.length - 1;
        }

        //
        if (target - A[index - 1] < A[index] - target) {
            System.out.println("target - A[index - 1] < A[index] - target");
            return index - 1;
        }
        return index;
    }
    // 找到第一个大于等于target的index
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
        
        if (A[start] >= target) {   // 如果target比最小元素都小，返回最初设定的start：0
            return start;
        }
        if (A[end] >= target) {
            return end;
        }
        return A.length;    // 如果target比所有元素都大，返回最右边元素的右边的index，
                            // 也就是length，对应（index == A.length），最后返回最大元素index：A.length - 1
    }

    @Test
    public void test(){
        System.out.println(closestNumber(new int[]{1, 2, 3}, 2));//1
        System.out.println(closestNumber(new int[]{1, 4, 6}, 3));//1
        System.out.println(closestNumber(new int[]{1, 4, 6}, 5));//2
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, 2));//1
    }

    @Test
    public void test01(){
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, -1));
        /*
                firstIndex: 0
                index == 0
                0
         */
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, 0));
        /*
                firstIndex: 0
                index == 0
                0
         */
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, 1));
        /*
                firstIndex: 0
                index == 0
                0
         */
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, 2));
        /*
                firstIndex: 1
                1
         */
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, 3));
        /*
                firstIndex: 1
                1
         */
        System.out.println(closestNumber(new int[]{1, 3, 3, 4}, 5));
        /*
                firstIndex: 4
                index == A.length
                3
         */
    }
}
/*

Given a target number and an integer array A sorted in ascending order, 
find the index i in A such that A[i] is closest to the given target.

Return -1 if there is no element in the array.

 Notice

There can be duplicate elements in the array, 
and we can return any of the indices with same value.

Have you met this question in a real interview? Yes
Example
Given [1, 2, 3] and target = 2, return 1.

Given [1, 4, 6] and target = 3, return 1.

Given [1, 4, 6] and target = 5, return 1 or 2.

Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.

Challenge 
O(logn) time complexity.

Tags 
Binary Search
Related Problems 
Medium K Closest Numbers In Sorted Array 19 %
Easy Last Position of Target 40 %
Easy Classical Binary Search 46 %
Easy First Position of Target 32 %
*/