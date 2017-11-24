package _01_Array.Wiggle_Sort;

import java.util.Arrays;

/*
Given an unsorted array nums, reorder it in-place such that

nums[0] <= nums[1] >= nums[2] <= nums[3]....
 Notice

Please complete the problem in-place.

Example
Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */

public class _280_Wiggle_Sort {
    // 9Ch
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        // Write your code here
        for(int i=1; i<nums.length; i++) {
            if((i%2==1 && (nums[i] < nums[i-1]) ||
                    (i%2==0) && (nums[i] > nums[i-1]))) {
                swap(nums, i-1, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

//-------------------------------------------------------------------------//

    //leetcode solution

    /*
    https://leetcode.com/problems/wiggle-sort/solution/
     */

    /*
    Approach #1 (Sorting) [Accepted]

    The obvious solution is to just sort the array first,
    then swap elements pair-wise starting from the second element. For example:

   [1, 2, 3, 4, 5, 6]
       ↑  ↑  ↑  ↑
       swap  swap

=> [1, 3, 2, 5, 4, 6]
     */

    public void wiggleSort2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    private void swap2(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    /*
    Time complexity : O(nlogn).
    The entire algorithm is dominated by the sorting step,
    which costs O(nlogn) time to sort nn elements.

    Space complexity : O(1).
    Space depends on the sorting implementation which, usually,
    costs O(1) auxiliary space if heapsort is used.


     */

//-------------------------------------------------------------------------//

    //Approach #2 (One-pass Swap) [Accepted]

    //Intuitively, we should be able to reorder it in one-pass.
    // As we iterate through the array, we compare the current element to its next
    // element and if the order is incorrect, we swap them.

    public void wiggleSort3(int[] nums) {
        boolean less = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            less = !less;
        }
    }


    /*
    We could shorten the code further by compacting the condition to a single line.
    Also observe the boolean value of less actually depends on whether the index is even or odd.
     */

    public void wiggleSort4(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((i % 2 == 0) && nums[i] > nums[i + 1])
                    || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }


    public void wiggleSort5(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }

    /*
    Complexity analysis

    Time complexity : O(n). In the worst case we swap at most n /2 times.
    An example input is [2,1,3,1,4,1].

    Space complexity : O(1).
     */



//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//




//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//




//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//




}
