package top100.Enhance._3Graph_PreClass;

public class _2_HeapAndBestFirstSearch_FindKthLargest {


public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k)
            return Integer.MIN_VALUE;

        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);  //Change to 0 based kth smallest
}
private int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
    if (start > end) {
        return Integer.MIN_VALUE;
    }

    int pivot = nums[end];// Take nums[end] as the pivot
    int pos = start;

    for (int i = start; i < end; i++) {
        if (nums[i] <= pivot) {//if curVal <= pivot, pos++, i++, swap(pos, i)
            swap(nums, pos++, i);
        }
    }

    swap(nums, pos, end);// set pivot to the partition position

    if (pos == k) {// Found
        return nums[pos];
    } else {
        return pos < k ? findKthLargest(nums, pos + 1, end, k) : findKthLargest(nums, start, pos - 1, k);
    }
}
private void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j]) {
            return;
        } else {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
}

}
