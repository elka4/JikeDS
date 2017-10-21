package _10_DS.Kth;
import org.junit.Test;

/** 461 Kth Smallest Numbers in Unsorted Array


 * Created by tianhuizhu on 6/28/17.
 */

//altered based on 9Ch

public class _461_Kth_Smallest_Numbers_in_Unsorted_Array {

    public int kthSmallest(int k, int[] nums) {

        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }
    public int kthLargest(int k, int[] nums) {

        return quickSelect(nums, 0, nums.length - 1, nums.length - k );
    }

    public int quickSelect(int[] A, int start, int end , int k) {

        if (start == end)
            return A[start];

        int left = start, right = end;
        int pivot = A[(start + end) / 2];

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }

            while (left <= right && A[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(A, left, right);
                left++;
                right--;
            }
        }

        if (right >= k && start <= right){
            return quickSelect(A, start, right, k);
        } else if(left <= k && left <= end){
            return quickSelect(A, left, end, k);
        } else {
            return A[k];
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test01(){
        int[] input = {5,3,7,2,6,1,4};
        System.out.println(kthLargest(1, input));
        System.out.println(kthLargest(2, input));
        System.out.println(kthLargest(3, input));
        System.out.println(kthLargest(4, input));
        System.out.println(kthLargest(5, input));
        System.out.println(kthLargest(6, input));
        System.out.println(kthLargest(7, input));

    }

    @Test
    public void test02(){
        int[] input = {5,3,7,2,6,1,4};
        System.out.println(kthSmallest(1, input));
        System.out.println(kthSmallest(2, input));
        System.out.println(kthSmallest(3, input));
        System.out.println(kthSmallest(4, input));
        System.out.println(kthSmallest(5, input));
        System.out.println(kthSmallest(6, input));
        System.out.println(kthSmallest(7, input));
        System.out.println(kthSmallest(0, input));
        System.out.println(kthSmallest(8, input));
    }


//////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////






}



