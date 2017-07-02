package J_2_Binary_Search.Optional_7;
import org.junit.Test;

import java.util.*;
/**
460
 K Closest Numbers In Sorted Array
 * Created by tianhuizhu on 6/28/17.
 */
public class _460_K_Closest_Numbers_In_Sorted_Array_Medium {

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

    //Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].

    //Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].
//http://localhost:63342/JikeDS/LintCodeBook/_book/Two-Pointers/K-Closest-Numbers-In-Sorted-Array.html
    public int[] kClosestNumbers2(int[] A, int target, int k) {
        // Write your code here
        if (A == null || A.length < k || k <= 0) {
            return new int[0];
        }

        int[] result = new int[k];

        int start = 0;
        int end = A.length;

        while (start < end - 1) {
            int mid = start + (end - start) / 2;
            if (A[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        int startIndex = Math.max(0, start - k + 1);
        int endIndex = Math.min(A.length - 1, end + k - 1);

        for (int i = 0; i < k; i++) {
            int startDiff = (start < 0) ? Integer.MAX_VALUE : Math.abs(A[start] - target);
            int endDiff = (end >= A.length) ? Integer.MAX_VALUE : Math.abs(A[end] - target);
            if (startDiff <= endDiff) {
                result[i] = A[start];
                start--;
            } else {
                result[i] = A[end];
                end++;
            }

        }

        return result;
    }
    @Test
    public void test01(){

        int[] input = new int[]{1,2,3};
        int[] result = kClosestNumbers(input, 2,3);
        for (int i:result
                ) {
            System.out.println(i);
        }
    }

    @Test
    public void test02(){

        int[] input = new int[]{1, 4, 6, 8};
        int[] result = kClosestNumbers(input, 3,3);
        for (int i:result
                ) {
            System.out.println(i);
        }
    }

    @Test
    public void test03(){

        int[] input = new int[]{1, 4, 6, 8};
        int[] result = kClosestNumbers(input, 3,3);
        Integer[] intList = new Integer[result.length];
        for (int i = 0; i < result.length;i++ ) {
            intList[i] = result[i];
        }


        List list = Arrays.asList(intList);
        //System.out.println(list);
        list.forEach(i -> System.out.println());
        //System.out.println(list);
//        for (int i : result) {
//
//        }
//        List<Integer> list = Arrays.asList(result);
        //result.forEach(n -> System.out.println(n));
    }

    @Test
    public  void test04(){
        int[] input = new int[]{1, 4, 6, 8};
        int[] result = kClosestNumbers(input, 3,3);
        List<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, Arrays.stream(result).boxed().toArray(Integer[]::new));
        System.out.println(list);
    }
    /*    public int[] kClosestNumbers(int[] A, int target, int k) {
        // Write your code here
        int[] result = new int[];
        if(A == null || A.length < k){
            return result;
        }
        int index = binarySearch(A, target);

        int start = index - 1;
        int end = index;
        for(int i = 0; i < k; i++){
            if(start < 0){

            } else if(end > end){

            } else{

            }
        }
    }

    private int binarySearch(int[] A, int target){
        int start = 0;
        int end = A.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(target == A[mid]){
                end = mid;
            } else if (target < A[mid]){
                end = mid;
            } else {
                start = mid;
            }
        }
        if(A[start] == target){
            return start;
        }
        if(A[end] == target){
            return end;
        }
        return A.length - 1;

    }*/

}
