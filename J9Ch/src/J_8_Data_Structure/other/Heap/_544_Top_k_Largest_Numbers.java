package J_8_Data_Structure.other.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 544 Top k Largest Numbers
 * Created by tianhuizhu on 6/28/17.
 */

public class _544_Top_k_Largest_Numbers {

    // base on heap
    /*
     * @param nums an integer array
     * @param k an integer
     * @return the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(k,
                new Comparator<Integer>() {
                    public int compare(Integer o1, Integer o2) {
                        if(o1 > o2) {
                            return 1;
                        } else if(o1 < o2) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });

        for (int i : nums) {
            maxheap.add(i);
            if (maxheap.size() > k) {
                maxheap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            result[k - i - 1] = maxheap.poll();
        }
        return result;
    }



///////////////////////////////////////////////////////////////////////////////////

    // base on quicksort
    /*
     * @param nums an integer array
     * @param k an integer
     * @return the top k largest numbers in array
     */
    public int[] topk2(int[] nums, int k) {
        // Write your code here
        quickSort(nums, 0, nums.length - 1, k);

        int[] topk = new int[k];
        for (int i = 0; i < k; ++i)
            topk[i] = nums[i];

        return topk;
    }

    private void quickSort(int[] A, int start, int end, int k) {
        if (start >= k)
            return;

        if (start >= end) {
            return;
        }

        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        Random rand =new Random(end - start + 1);
        int index = rand.nextInt(end - start + 1) + start;
        int pivot = A[index];

        // key point 2: every time you compare left & right, it should be
        // left <= right not left < right
        while (left <= right) {
            // key point 3: A[left] < pivot not A[left] <= pivot
            while (left <= right && A[left] > pivot) {
                left++;
            }
            // key point 3: A[right] > pivot not A[right] >= pivot
            while (left <= right && A[right] < pivot) {
                right--;
            }

            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        quickSort(A, start, right, k);
        quickSort(A, left, end, k);
    }


/*
Given an integer array, find the top k largest numbers in it.

Have you met this question in a real interview? Yes
Example
Given [3,10,1000,-99,4,100] and k = 3.
Return [1000, 100, 10].


 */

}
